package net.datenwerke.security.service.usermanager;

import static java.util.stream.Collectors.toSet;
import static net.datenwerke.rs.utils.exception.shared.LambdaExceptionUtil.rethrowConsumer;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.utils.crypto.PasswordHasher;
import net.datenwerke.rs.utils.simplequery.PredicateType;
import net.datenwerke.rs.utils.simplequery.annotations.QueryByAttribute;
import net.datenwerke.rs.utils.simplequery.annotations.QueryById;
import net.datenwerke.rs.utils.simplequery.annotations.SimpleQuery;
import net.datenwerke.security.service.eventlogger.annotations.FireForceRemoveEntityEvents;
import net.datenwerke.security.service.eventlogger.annotations.FireRemoveEntityEvents;
import net.datenwerke.security.service.treedb.SecuredTreeDBManagerImpl;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode__;
import net.datenwerke.security.service.usermanager.entities.Group;
import net.datenwerke.security.service.usermanager.entities.Group__;
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit;
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit__;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.entities.User__;
import net.datenwerke.security.service.usermanager.hooks.ChangePasswordHook;
import net.datenwerke.security.service.usermanager.hooks.PasswordSetHook;
import net.datenwerke.treedb.service.treedb.TreeDBService;

/**
 * ReportServer's User Management Component
 * 
 *
 */
@Singleton
public class UserManagerServiceImpl extends SecuredTreeDBManagerImpl<AbstractUserManagerNode>
      implements UserManagerService {

   /**
    * A reference to the security service
    */
   private final Provider<EntityManager> entityManagerProvider;
   private final PasswordHasher passwordHasher;
   private final HookHandlerService hookHandlerService;

   @Inject
   public UserManagerServiceImpl(
         TreeDBService treeDB, 
         Provider<EntityManager> entityManagerProvider,
         PasswordHasher passwordHasher, 
         HookHandlerService hookHandlerService
         ) {

      /* store objects */
      this.entityManagerProvider = entityManagerProvider;
      this.passwordHasher = passwordHasher;
      this.hookHandlerService = hookHandlerService;
   }

   @Override
   @QueryByAttribute(where = User__.username)
   public User getUserByName(String name) {
      return null; // magic
   }

   @Override
   public Collection<User> getUsersByMail(String email) {
      if (null == email)
         return Collections.emptyList();

      EntityManager em = entityManagerProvider.get();
      Query query = em.createQuery("FROM " + User.class.getSimpleName() + " where lower(email) = :mail");
      query.setParameter("mail", email.toLowerCase());

      try {
         return query.getResultList();
      } catch (NoResultException e) {
         return null;
      }
   }

   @Override
   public User getUserByMail(String email) {
      if (null == email)
         return null;

      EntityManager em = entityManagerProvider.get();
      Query query = em.createQuery("FROM " + User.class.getSimpleName() + " where lower(email) = :mail");
      query.setParameter("mail", email.toLowerCase());

      try {
         return (User) query.getSingleResult();
      } catch (NoResultException e) {
         return null;
      }
   }

   @Override
   @QueryByAttribute(where = Group__.name)
   public Group getGroupByName(String name) {
      return null; // magic
   }

   @Override
   @QueryByAttribute(where = OrganisationalUnit__.name)
   public List<OrganisationalUnit> getOUsByName(String name) {
      return null; // magic
   }

   /**
    * Removes the node and takes care of database constraints.
    */
   @Override
   @FireRemoveEntityEvents
   public void remove(AbstractUserManagerNode node) {
      doPreRemove(node);

      /* do remove */
      super.remove(node);
   }

   /**
    * Removes the node and takes care of database constraints.
    */
   @Override
   @FireForceRemoveEntityEvents
   public void forceRemove(AbstractUserManagerNode node) {
      doPreRemove(node);

      /* do remove */
      super.forceRemove(node);
   }

   private void doPreRemove(AbstractUserManagerNode node) {
      /* do anything prior to remove */
      if (node instanceof User)
         doPreRemoveUser((User) node);
      if (node instanceof Group)
         doPreRemoveGroup((Group) node);
      if (node instanceof OrganisationalUnit)
         doPreRemoveOu((OrganisationalUnit) node);
   }

   /**
    * Removes the user from all groups
    * 
    * @param user
    */
   private void doPreRemoveUser(final User user) {
      user.getGroups().forEach(group -> group.removeUser(user));
   }

   protected void doPreRemoveGroup(final Group group) {
      getGroupsWithMember(group).forEach(g -> g.removeReferencedGroup(group));
   }

   protected void doPreRemoveOu(final OrganisationalUnit ou) {
      getGroupsWithMember(ou).forEach(g -> g.removeOu(ou));
   }

   @QueryByAttribute(where = Group__.referencedGroups, type = PredicateType.IS_MEMBER)
   @Override
   public List<Group> getGroupsWithMember(Group group) {
      return null; // magic
   }

   @QueryByAttribute(where = Group__.ous, type = PredicateType.IS_MEMBER)
   @Override
   public List<Group> getGroupsWithMember(OrganisationalUnit ou) {
      return null; // magic
   }

   @Override
   public boolean userInFolk(User user, AbstractUserManagerNode folk) {
      return userInFolk(user, folk, new HashSet<Group>());
   }

   public boolean userInFolk(User user, AbstractUserManagerNode folk, Set<Group> seenGroups) {
      if (folk instanceof User)
         return user.equals(folk);
      if (folk instanceof Group) {
         seenGroups.add((Group) folk);
         if (((Group) folk).getUsers().contains(user))
            return true;
         for (OrganisationalUnit ou : ((Group) folk).getOus()) {
            if (ou.isAncestorOf(user))
               return true;
         }
         for (Group group : ((Group) folk).getAllReferencedGroups()) {
            if (!seenGroups.contains(group)) {
               if (userInFolk(user, group, seenGroups))
                  return true;
            }
         }
      }
      if (folk instanceof OrganisationalUnit) {
         if (folk.isAncestorOf(user))
            return true;
      }
      return false;
   }

   @Override
   public Collection<Group> getReferencedGroups(User user) {
      Collection<Group> refGroups = new HashSet<Group>();

      /* get direct groups */
      for (Group g : user.getGroups())
         refGroups.add(g);

      /* get groups from ous in rootline */
      for (AbstractUserManagerNode parent : user.getRootLine()) {
         if (parent instanceof OrganisationalUnit) {
            for (Group g : getGroupsWithMember((OrganisationalUnit) parent))
               refGroups.add(g);
         }
      }

      /* recursively add groups */
      addReferencedGroups(refGroups, new HashSet<Group>(refGroups), new HashSet<Group>());

      return refGroups;
   }

   private void addReferencedGroups(Collection<Group> refGroups, Collection<Group> toCheck, Collection<Group> checked) {
      if (toCheck.isEmpty())
         return;

      Collection<Group> newGroups = new HashSet<Group>();

      for (Group g : toCheck) {
         if (!checked.contains(g)) {
            newGroups.addAll(getGroupsWithMember(g));
            checked.add(g);
         }
      }

      refGroups.addAll(newGroups);

      addReferencedGroups(refGroups, newGroups, checked);
   }

   @Override
   @SimpleQuery
   public Collection<Group> getAllGroups() {
      return null; // by magic
   }

   @Override
   @SimpleQuery
   public Collection<OrganisationalUnit> getAllOUs() {
      return null; // by magic
   }

   @Override
   @SimpleQuery
   public Collection<User> getAllUsers() {
      return null; // by magic
   }

   @Override
   public Set<User> getUsers(Collection<Long> ids) {
      return getUsers(ids, false);
   }

   @Override
   public Set<User> getUsers(Collection<Long> ids, boolean dereferenceGroups) {
      Set<User> users = new HashSet<User>();
      for (Long id : ids) {
         try {
            AbstractUserManagerNode node = getNodeById(id);
            if (node instanceof User) {
               User user = (User) node;
               users.add(user);
            } else if (dereferenceGroups && node instanceof Group) {
               /* add group */
               Group group = (Group) node;
               users.addAll(group.getUsers());

               /* add referenced groups */
               for (Group g : group.getAllReferencedGroups())
                  users.addAll(g.getUsers());
            }
         } catch (NoResultException e) {
            // TODO error handling
         }
      }

      return users;
   }

   @Override
   public Set<Group> getGroups(Collection<Long> ids) {
      return ids
         .stream()
         .map(this::getNodeById)
         .filter(node -> node instanceof Group)
         .map(node -> (Group) node)
         .collect(toSet());
   }

   @Override
   public void setPassword(final User user, final String newPassword) {
      user.setPassword(newPassword, passwordHasher);
      
      hookHandlerService.getHookers(PasswordSetHook.class)
         .forEach(h -> h.passwordWasSet(user));
   }

   @Override
   public void changePassword(final User user, final String oldPassword, final String newPassword) throws ExpectedException {
      if (null != user.getPassword() && !passwordHasher.validatePassword(user.getPassword(), oldPassword))
         throw new ExpectedException("old password wrong");

      hookHandlerService.getHookers(ChangePasswordHook.class)
         .forEach( rethrowConsumer(cph -> cph.beforePasswordChanged(user, newPassword )));

      user.setPassword(newPassword, passwordHasher);
      merge(user);

      hookHandlerService.getHookers(ChangePasswordHook.class)
         .forEach( rethrowConsumer(cph ->  cph.afterPasswordChanged(user)) );
   }

   @Override
   public void changePassword(String username, String oldPassword, String newPassword) throws ExpectedException {
      User user = getUserByName(username);
      changePassword(user, oldPassword, newPassword);
   }

   @Override
   @QueryByAttribute(where = AbstractUserManagerNode__.parent, type = PredicateType.IS_NULL)
   public List<AbstractUserManagerNode> getRoots() {
      return null; // magic
   }

   @Override
   @SimpleQuery
   public List<AbstractUserManagerNode> getAllNodes() {
      return null;
   }

   @Override
   @QueryById
   public AbstractUserManagerNode getNodeById(long id) {
      return null; // magic
   }

   @Override
   public Set<User> getAllTransitiveUsers(AbstractUserManagerNode node) {
      Set<User> ulist = new HashSet<User>();

      if (node instanceof User) {
         ulist.add((User) node);
         return ulist;
      }

      if (node instanceof Group) {
         // process inherited groups
         for (Group g : ((Group) node).getReferencedGroups())
            ulist.addAll(getAllTransitiveUsers(g));

         // process inherited OUs
         for (OrganisationalUnit ou : ((Group) node).getOus())
            ulist.addAll(getAllTransitiveUsers(ou));

         // add users of current node
         for (User user : ((Group) node).getUsers())
            ulist.addAll(getAllTransitiveUsers(user));

      } else if (node instanceof OrganisationalUnit) {
         for (AbstractUserManagerNode child : node.getChildren()) {
            if (child instanceof User)
               ulist.addAll(getAllTransitiveUsers(child));

            if (child instanceof Group)
               ulist.addAll(getAllTransitiveUsers((Group) child));

            if (child instanceof OrganisationalUnit)
               ulist.addAll(getAllTransitiveUsers((OrganisationalUnit) child));
         }
      }
      return ulist;
   }

   @Override
   public Set<OrganisationalUnit> getOUs(Collection<Long> ids) {
      return ids
         .stream()
         .map(this::getNodeById)
         .filter(node -> node instanceof OrganisationalUnit)
         .map(node -> (OrganisationalUnit) node)
         .collect(toSet());
   }

}
