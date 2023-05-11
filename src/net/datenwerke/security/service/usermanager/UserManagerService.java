package net.datenwerke.security.service.usermanager;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.Group;
import net.datenwerke.security.service.usermanager.entities.OrganisationalUnit;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

/**
 * 
 *
 */
public interface UserManagerService extends TreeDBManager<AbstractUserManagerNode> {

   public static final String MODULE_NAME = "UserManager"; //$NON-NLS-1$

   public static final String SECUREE_ID = "UserManagerService_Default"; //$NON-NLS-1$

   /**
    * Finds a {@link User} by name
    * 
    * @param name The {@link User}'s name
    * @return The corresponding {@link User}
    */
   abstract User getUserByName(String name);
   
   abstract User getUserOrNull(String username);

   /**
    * Finds a {@link User} by email
    * 
    * @return The corresponding {@link User}
    */
   abstract User getUserByMail(String mail);

   /**
    * Finds a {@link Group} by name
    * 
    * @param name The {@link Group}'s name
    * @return The corresponding {@link Group}
    */
   abstract Group getGroupByName(String name);

   /**
    * Searches for a {@link OrganisationalUnit} by its name
    * 
    * @param name The name of the {@link OrganisationalUnit}
    * @return The corresponding {@link OrganisationalUnit}
    */
   abstract List<OrganisationalUnit> getOUsByName(String name);

   /**
    * Searches for a {@link AbstractUserManagerNode} by its id
    * 
    * @param id The ID
    * @return The {@link AbstractUserManagerNode} identified by the given ID
    */
   abstract AbstractUserManagerNode getNodeById(long id);

   /**
    * Returns a {@link Collection} of all existing {@link User}s
    * 
    * @return A {@link Collection} of {@link User}s
    */
   abstract Collection<User> getAllUsers();

   /**
    * Returns a {@link Collection} of all existing {@link Group}s
    * 
    * @return A {@link Collection} of {@link Group}s
    */
   abstract Collection<Group> getAllGroups();

   abstract Collection<OrganisationalUnit> getAllOUs();

   /**
    * Sets the {@link User}s password
    * 
    * @param user        The {@link User}
    * @param newPassword The new password
    */
   abstract void setPassword(User user, String newPassword);

   void changePassword(User user, String oldPassword, String newPassword) throws ExpectedException;

   void changePassword(String username, String oldPassword, String newPassword) throws ExpectedException;

   /**
    * Persists the submitted {@link AbstractUserManagerNode}
    * 
    * @param node The {@link AbstractUserManagerNode}
    */
   void persist(AbstractUserManagerNode node);

   /**
    * Merge's the submitted {@link AbstractUserManagerNode} with the representation
    * in the database
    * 
    * @param node The {@link AbstractUserManagerNode}
    */
   AbstractUserManagerNode merge(AbstractUserManagerNode node);

   /**
    * Removes the submitted {@link AbstractUserManagerNode} from the database
    * 
    * @param node {@link AbstractUserManagerNode}
    */
   void remove(AbstractUserManagerNode node);

   /**
    * Tests whether the submitted {@link User} is part of the folk.
    * 
    * @param user The {@link User} to test
    * @param folk The {@link AbstractUserManagerNode} (the folk)
    * @return true if the {@link User} is part of folk; false otherwise
    */
   boolean userInFolk(User user, AbstractUserManagerNode folk);

   /**
    * Returns a {@link Set} of {@link User}s defined by the given
    * {@link Collection} of IDs
    * 
    * @param ids A {@link Collection} of {@link Long}s
    * @return A {@link Set} of {@link User}s
    */
   abstract Set<User> getUsers(Collection<Long> ids);

   abstract Set<OrganisationalUnit> getOUs(Collection<Long> ids);

   /**
    * Returns {@link User} objects for all given IDs (be they user or group)
    * 
    * @param ids               A {@link Collection} of {@link Long}s containing the
    *                          IDs
    * @param dereferenceGroups Set to <b>true</b> if the result should contain
    *                          groups
    * @return A {@link Set} of {@link User}s
    */
   abstract Set<User> getUsers(Collection<Long> ids, boolean dereferenceGroups);

   /**
    * Returns a {@link Set} of {@link Group}s defined by the given
    * {@link Collection} of IDs
    * 
    * @param groupIds A {@link Collection} of {@link Long}s
    * @return A {@link Set} of {@link Group}s
    */
   abstract Set<Group> getGroups(Collection<Long> groupIds);

   Collection<User> getUsersByMail(String email);

   List<Group> getGroupsWithMember(Group group);

   List<Group> getGroupsWithMember(OrganisationalUnit ou);

   /**
    * Returns all groups in which the user is a direct or indirect member.
    * 
    * @param user the user to fetch all groups from
    */
   Collection<Group> getReferencedGroups(User user);

   /**
    * Returns all users underneath the node.
    * 
    * @param node
    */
   Set<User> getAllTransitiveUsers(AbstractUserManagerNode node);
   
   /**
    * Returns groups in which the user is a direct member.
    * 
    * @param user the user to fetch direct groups from
    */
   Collection<Group> getDirectGroups(User user);
   
   /**
    * Returns groups in which the user is an indirect member.
    * 
    * @param user the user to fetch indirect groups from
    */
   Collection<Group> getIndirectGroups(User user);
   
   /**
    * Returns the number of active users in the system, that is, not inhibited and
    * not with accounts not expired.
    * 
    * @return
    */
   long getNumberOfActiveUsers();
   
   long getNumberOfInhibitedUsers();
   
   long getNumberOfExpiredUsers();

}