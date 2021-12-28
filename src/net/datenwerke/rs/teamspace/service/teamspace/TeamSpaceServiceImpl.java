package net.datenwerke.rs.teamspace.service.teamspace;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceApp;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceMember;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceMember__;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceRole;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace__;
import net.datenwerke.rs.teamspace.service.teamspace.genrights.TeamSpaceSecurityTarget;
import net.datenwerke.rs.teamspace.service.teamspace.hooks.TeamSpaceAppDefinitionProviderHook;
import net.datenwerke.rs.teamspace.service.teamspace.security.TeamSpaceSecuree;
import net.datenwerke.rs.teamspace.service.teamspace.security.rights.TeamSpaceAdministrator;
import net.datenwerke.rs.utils.simplequery.annotations.Join;
import net.datenwerke.rs.utils.simplequery.annotations.Predicate;
import net.datenwerke.rs.utils.simplequery.annotations.QueryByAttribute;
import net.datenwerke.rs.utils.simplequery.annotations.QueryById;
import net.datenwerke.rs.utils.simplequery.annotations.SimpleQuery;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.eventlogger.annotations.FireForceRemoveEntityEvents;
import net.datenwerke.security.service.eventlogger.annotations.FireMergeEntityEvents;
import net.datenwerke.security.service.eventlogger.annotations.FirePersistEntityEvents;
import net.datenwerke.security.service.eventlogger.annotations.FireRemoveEntityEvents;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.UserPropertiesService;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.entities.UserProperty;

/**
 * 
 *
 */
public class TeamSpaceServiceImpl implements TeamSpaceService {

   private final Provider<AuthenticatorService> authenticatorServiceProvider;
   private final Provider<EntityManager> entityManagerProvider;
   private final HookHandlerService hookHandler;
   private final SecurityService securityService;
   private final UserManagerService userService;
   private final UserPropertiesService userPropertiesService;

   @Inject
   public TeamSpaceServiceImpl(Provider<AuthenticatorService> authenticatorServiceProvider,
         Provider<EntityManager> entityManagerProvider, HookHandlerService hookHandler, SecurityService securityService,
         UserManagerService userService, UserPropertiesService userPropertiesService) {

      /* store objects */
      this.authenticatorServiceProvider = authenticatorServiceProvider;
      this.entityManagerProvider = entityManagerProvider;
      this.hookHandler = hookHandler;
      this.securityService = securityService;
      this.userService = userService;
      this.userPropertiesService = userPropertiesService;
   }

   @Override
   @QueryById
   public TeamSpace getTeamSpaceById(@Named("id") Long id) {
      return null; // by magic
   }

   @Override
   @QueryByAttribute(where = TeamSpace__.name)
   public TeamSpace getTeamSpaceByName(String name) {
      return null; // by magic
   }

   @Override
   @QueryByAttribute(where = TeamSpace__.name)
   public Collection<TeamSpace> getTeamSpacesByName(String name) {
      return null; // by magic
   }

   @Override
   public void provideAccess(TeamSpace teamSpace, User user, TeamSpaceRole role) {
      if (!mayAccess(user, teamSpace)) {
         TeamSpaceMember member = new TeamSpaceMember();
         member.setFolk(user);
         member.setRole(role);
         teamSpace.addMember(member);

         persist(member);
         merge(teamSpace);
      }
   }

   @Override
   public TeamSpace getPrimarySpace() {
      User currentUser = authenticatorServiceProvider.get().getCurrentUser();
      return getPrimarySpace(currentUser);
   }

   @Override
   public TeamSpace getExplicitPrimarySpace() {
      User currentUser = authenticatorServiceProvider.get().getCurrentUser();
      UserProperty property = userPropertiesService.getProperty(currentUser, USER_PROPERTY_PRIMARY_TEAMSPACE);
      if (null != property) {
         try {
            long id = Long.valueOf(property.getValue());
            TeamSpace teamSpace = getTeamSpaceById(id);
            if (null != teamSpace && mayAccess(currentUser, teamSpace))
               return teamSpace;
         } catch (NumberFormatException e) {
         } catch (NoResultException e) {
         }
      }

      return null;
   }

   @Override
   public TeamSpace getPrimarySpace(User user) {
      UserProperty property = userPropertiesService.getProperty(user, USER_PROPERTY_PRIMARY_TEAMSPACE);
      if (null != property) {
         try {
            long id = Long.valueOf(property.getValue());
            TeamSpace teamSpace = getTeamSpaceById(id);
            if (null != teamSpace && getTeamSpaces(user).contains(teamSpace))
               return teamSpace;
         } catch (NumberFormatException e) {
         } catch (NoResultException e) {
         }
      }

      /* get first */
      Collection<TeamSpace> spaces = getTeamSpaces(user);
      if (null != spaces && !spaces.isEmpty()) {
         Iterator<TeamSpace> it = spaces.iterator();
         while (it.hasNext()) {
            TeamSpace teamSpace = it.next();
            if (mayAccess(user, teamSpace))
               return teamSpace;
         }
      }
      return null;
   }

   @Override
   public void setPrimarySpace(TeamSpace teamSpace) {
      User currentUser = authenticatorServiceProvider.get().getCurrentUser();

      UserProperty property = userPropertiesService.getProperty(currentUser, USER_PROPERTY_PRIMARY_TEAMSPACE);
      if (null == property) {
         if (null != teamSpace) {
            property = new UserProperty(USER_PROPERTY_PRIMARY_TEAMSPACE, String.valueOf(teamSpace.getId()));
            userPropertiesService.setProperty(currentUser, property);
         }
      } else {
         if (null != teamSpace)
            property.setValue(teamSpace.getId());
         else
            userPropertiesService.removeProperty(currentUser, property);
      }

      userService.merge(currentUser);
   }

   @Override
   public Collection<TeamSpace> getTeamSpaces() {
      User user = authenticatorServiceProvider.get().getCurrentUser();
      Collection<TeamSpace> teamspaces = getTeamSpaces(user);
      return teamspaces;
   }

   @Override
   public Collection<TeamSpace> getTeamSpaces(User user) {
      List<Long> idList = new ArrayList<>();
      for (TeamSpace space : getAllTeamSpaces())
         if (mayAccess(user, space))
            idList.add(space.getId());

      if (null == idList || idList.isEmpty())
         return Collections.emptySet();

      StringBuilder query = new StringBuilder("from ").append(TeamSpace.class.getSimpleName())
            .append(" WHERE id IN :ids");

      List<TeamSpace> teamspaces = entityManagerProvider.get().createQuery(query.toString()).setParameter("ids", idList)
            .getResultList();

      return teamspaces;
   }

   @Override
   @SimpleQuery(join = @Join(joinAttribute = TeamSpace__.members, where = @Predicate(attribute = TeamSpaceMember__.folk, value = "folk")))
   public Collection<TeamSpace> getTeamSpacesWithMemberFor(@Named("folk") AbstractUserManagerNode folk) {
      return null; // magic
   }

   @Override
   public Collection<TeamSpace> getOwnedTeamSpaces() {
      User user = authenticatorServiceProvider.get().getCurrentUser();
      return getOwnedTeamSpaces(user);
   }

   @Override
   @QueryByAttribute(where = TeamSpace__.owner)
   public Collection<TeamSpace> getOwnedTeamSpaces(User owner) {
      return null; // by magic
   }

   @Override
   @SimpleQuery
   public Collection<TeamSpace> getAllTeamSpaces() {
      return null; // by magic
   }

   @Override
   @FirePersistEntityEvents
   public void persist(TeamSpace teamSpace) {
      EntityManager em = entityManagerProvider.get();
      em.persist(teamSpace);
   }

   @Override
   @FirePersistEntityEvents
   public void persist(TeamSpaceMember member) {
      EntityManager em = entityManagerProvider.get();
      em.persist(member);
   }

   @Override
   @FirePersistEntityEvents
   public void persist(TeamSpaceApp app) {
      EntityManager em = entityManagerProvider.get();
      em.persist(app);
   }

   @Override
   @FireMergeEntityEvents
   public TeamSpace merge(TeamSpace teamSpace) {
      EntityManager em = entityManagerProvider.get();
      teamSpace = em.merge(teamSpace);

      return teamSpace;
   }

   @Override
   @FireMergeEntityEvents
   public TeamSpaceMember merge(TeamSpaceMember member) {
      EntityManager em = entityManagerProvider.get();
      return em.merge(member);
   }

   @Override
   @FireMergeEntityEvents
   public TeamSpaceApp merge(TeamSpaceApp app) {
      EntityManager em = entityManagerProvider.get();
      return em.merge(app);
   }

   @Override
   @FireRemoveEntityEvents
   public void remove(TeamSpace teamSpace) {
      doRemove(teamSpace);
   }

   @Override
   @FireForceRemoveEntityEvents
   public void forceRemove(TeamSpace teamSpace) {
      doRemove(teamSpace);
   }

   protected void doRemove(TeamSpace teamSpace) {
      /* remove members */
      Iterator<TeamSpaceMember> members = teamSpace.getMembers().iterator();
      while (members.hasNext())
         remove(members.next());

      /* remove team space */
      EntityManager em = entityManagerProvider.get();
      teamSpace = em.find(teamSpace.getClass(), teamSpace.getId());
      if (null != teamSpace)
         em.remove(teamSpace);
   }

   @Override
   @FireRemoveEntityEvents
   public void remove(TeamSpaceMember member) {
      EntityManager em = entityManagerProvider.get();
      member = em.find(member.getClass(), member.getId());
      if (null != member)
         em.remove(member);
   }

   @Override
   @FireRemoveEntityEvents
   public void remove(TeamSpaceApp app) {
      EntityManager em = entityManagerProvider.get();
      app = em.find(app.getClass(), app.getId());
      if (null != app)
         em.remove(app);
   }

   @Override
   public TeamSpace createTeamSpace() {
      User user = authenticatorServiceProvider.get().getCurrentUser();
      if (null == user)
         throw new IllegalStateException("Could not determine current user");
      return createTeamSpace(user);
   }

   @Override
   public TeamSpace createTeamSpace(User user) {
      TeamSpace teamSpace = new TeamSpace();
      teamSpace.setOwner(user);

      persist(teamSpace);

      Collection<String> appIds = new ArrayList<String>();
      for (TeamSpaceAppDefinition app : getInstalledApps())
         appIds.add(app.getAppId());

      setInstalledApps(teamSpace, appIds);

      merge(teamSpace);

      return teamSpace;
   }

   @Override
   public boolean appExists(String appId) {
      return null != getAppDefinitionById(appId);
   }

   @Override
   public TeamSpaceAppDefinition getAppDefinitionById(String appId) {
      for (TeamSpaceAppDefinition appDef : getInstalledApps())
         if (appDef.getAppId().equals(appId))
            return appDef;
      return null;
   }

   public List<TeamSpaceAppDefinition> getInstalledApps() {
      List<TeamSpaceAppDefinition> apps = new ArrayList<TeamSpaceAppDefinition>();

      List<TeamSpaceAppDefinitionProviderHook> appProviders = hookHandler
            .getHookers(TeamSpaceAppDefinitionProviderHook.class);
      for (TeamSpaceAppDefinitionProviderHook appProvider : appProviders) {
         TeamSpaceAppDefinition appDefinition = appProvider.getObject();

         apps.add(appDefinition);
      }

      return apps;
   }

   @Override
   public TeamSpaceApp installTeamSpaceApp(TeamSpace teamSpace, String appId) {
      if (!appExists(appId))
         throw new IllegalArgumentException("app unknown: " + appId);

      /* init app */
      TeamSpaceApp app = new TeamSpaceApp();
      app.setInstalled(true);
      app.setType(appId);

      /* add app and persist */
      teamSpace.addApp(app);
      persist(app);

      /* get app definition and tell it to initialize the app */
      getAppDefinitionById(appId).initializeApp(teamSpace, app);

      return app;
   }

   @Override
   public boolean isAdmin(TeamSpace teamSpace) {
      return hasRole(teamSpace, TeamSpaceRole.ADMIN);
   }

   @Override
   public boolean isAdmin(User user, TeamSpace teamSpace) {
      return hasRole(user, teamSpace, TeamSpaceRole.ADMIN);
   }

   @Override
   public boolean isUser(TeamSpace teamSpace) {
      return hasRole(teamSpace, TeamSpaceRole.USER);
   }

   @Override
   public boolean isUser(User user, TeamSpace teamSpace) {
      return hasRole(user, teamSpace, TeamSpaceRole.USER);
   }

   @Override
   public boolean isGuest(TeamSpace teamSpace) {
      return hasRole(teamSpace, TeamSpaceRole.GUEST);
   }

   @Override
   public boolean isGuest(User user, TeamSpace teamSpace) {
      return hasRole(user, teamSpace, TeamSpaceRole.GUEST);
   }

   @Override
   public boolean isManager(TeamSpace teamSpace) {
      return hasRole(teamSpace, TeamSpaceRole.MANAGER);
   }

   @Override
   public boolean isManager(User user, TeamSpace teamSpace) {
      return hasRole(user, teamSpace, TeamSpaceRole.MANAGER);
   }

   @Override
   public boolean isOwner(TeamSpace teamSpace) {
      User currentUser = authenticatorServiceProvider.get().getCurrentUser();
      return isOwner(currentUser, teamSpace);
   }

   private boolean isOwner(User user, TeamSpace teamSpace) {
      return user.equals(teamSpace.getOwner());
   }

   @Override
   public boolean hasRole(TeamSpace teamSpace, TeamSpaceRole roleToHave) {
      if (null == teamSpace)
         return true;
      if (isGlobalTsAdmin() || isOwner(teamSpace))
         return true;

      TeamSpaceRole role = getRole(teamSpace);
      if (null == role)
         return false;
      return roleToHave.compareTo(role) >= 0;
   }

   @Override
   public boolean hasRole(User user, TeamSpace teamSpace, TeamSpaceRole roleToHave) {
      if (isGlobalTsAdmin(user) || isOwner(user, teamSpace))
         return true;
      TeamSpaceRole role = getRole(user, teamSpace);
      if (null == role)
         return false;
      return roleToHave.compareTo(role) >= 0;
   }

   @Override
   public TeamSpaceRole getRole(User user, TeamSpace teamSpace) {
      TeamSpaceRole role = null;

      for (TeamSpaceMember member : teamSpace.getMembers()) {
         if (userService.userInFolk(user, member.getFolk())) {
            if (null == role || role.compareTo(member.getRole()) >= 0)
               role = member.getRole();
         }
      }

      return role;
   }

   @Override
   public TeamSpaceMember getMemberFor(TeamSpace teamSpace, AbstractUserManagerNode folk) {
      for (TeamSpaceMember member : teamSpace.getMembers())
         if (folk.equals(member.getFolk()))
            return member;
      return null;
   }

   @QueryByAttribute(where = TeamSpaceMember__.folk)
   protected Collection<TeamSpaceMember> getMembersFor(AbstractUserManagerNode folk) {
      return null;
   }

   @Override
   public TeamSpaceRole getRole(TeamSpace teamSpace) {
      User currentUser = authenticatorServiceProvider.get().getCurrentUser();
      return getRole(currentUser, teamSpace);
   }

   @Override
   public boolean isGlobalTsAdmin() {
      return securityService.checkRights(TeamSpaceSecurityTarget.class, TeamSpaceSecuree.class,
            TeamSpaceAdministrator.class);
   }

   @Override
   public boolean isGlobalTsAdmin(User user) {
      return securityService.checkRights(user, TeamSpaceSecurityTarget.class, TeamSpaceSecuree.class,
            TeamSpaceAdministrator.class);
   }

   @Override
   public boolean mayAccess(TeamSpace teamSpace) {
      securityService.assertRights(TeamSpaceSecurityTarget.class, Read.class);

      return isGlobalTsAdmin() || isGuest(teamSpace) || isOwner(teamSpace);
   }

   @Override
   public boolean mayAccess(User user, TeamSpace teamSpace) {
      return isGlobalTsAdmin(user) || isOwner(user, teamSpace) || isGuest(user, teamSpace);
   }

   @Override
   public void setInstalledApps(TeamSpace teamSpace, Collection<String> appIds) {
      for (String appId : appIds) {
         TeamSpaceApp app = teamSpace.getAppByType(appId);
         if (null != app)
            app.setInstalled(true);
         else
            installTeamSpaceApp(teamSpace, appId);
      }

      /* uninstall all other apps */
      for (TeamSpaceApp app : teamSpace.getApps())
         if (!appIds.contains(app.getType()))
            app.setInstalled(false);
   }

   @Override
   public void assertAccess(TeamSpace ts) {
      if (null == ts || !mayAccess(ts))
         throw new ViolatedSecurityException(
               "User does not have access to TeamSpace: " + (null == ts ? "" : ts.getId()));
   }

   @Override
   public void assertIsUser(TeamSpace teamSpace) {
      if (!isUser(teamSpace))
         throw new ViolatedSecurityException(
               "User does not have User rights in TeamSpace: " + (null == teamSpace ? "" : teamSpace.getId()));
   }

}
