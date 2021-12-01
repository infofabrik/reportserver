package net.datenwerke.rs.teamspace.service.teamspace;

import java.util.Collection;

import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceApp;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceMember;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceRole;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 *
 */
public interface TeamSpaceService {

	public static final String USER_PROPERTY_PRIMARY_TEAMSPACE = "teamspace:primaryTeamSpace";
	
	TeamSpace getTeamSpaceById(Long id);
	
	/**
	 * Retrieves the primary {@link TeamSpace} of the current user.
	 * 
	 * @return The primary {@link TeamSpace} of the current user.
	 */
	TeamSpace getPrimarySpace();
	
	/**
	 * Retrieves the primary {@link TeamSpace} of the current user if this is explicitely set.
	 * 
	 * @return The primary {@link TeamSpace} of the current user if this is explicitely set.
	 */
	TeamSpace getExplicitPrimarySpace();
	
	/**
	 * Retrieves the primary {@link TeamSpace} of the given {@link User}.
	 * 
	 * @param user The {@link User}
	 * @return The primary {@link TeamSpace} of the given {@link User}
	 */
	TeamSpace getPrimarySpace(User user);
	
	/**
	 * Sets the primary {@link TeamSpace} for the current user
	 * 
	 * @param teamSpace The new primary {@link TeamSpace}
	 */
	public void setPrimarySpace(TeamSpace teamSpace);
	

	/**
	 * Creates a new {@link TeamSpace} with the current user as owner.
	 * 
	 * @return The newly created {@link TeamSpace}
	 */
	TeamSpace createTeamSpace();
	
	/**
	 * Creates a new {@link TeamSpace} with the given {@link User} as owner
	 * 
	 * @param user The {@link User}
	 * @return The newly created {@link TeamSpace}
	 */
	TeamSpace createTeamSpace(User user);

	/**
	 * Returns the current user's {@link TeamSpace}s
	 * 
	 * @return A {@link Collection} of the current user's {@link TeamSpace}s
	 */
	Collection<TeamSpace> getTeamSpaces();

	/**
	 * Returns the {@link TeamSpace}s that can be accessed by a given folk 
	 * 
	 * @param user The {@link User}
	 * @return A {@link Collection} of {@link TeamSpace}s
	 */
	Collection<TeamSpace> getTeamSpaces(User user);

	/**
	 * Returns the current user's owned {@link TeamSpace}s
	 * 
	 * @return A {@link Collection} of the {@link TeamSpace} owned by the current user
	 */
	Collection<TeamSpace> getOwnedTeamSpaces();

	/**
	 * Returns the {@link User}'s owned {@link TeamSpace}s
	 * 
	 * @param user The {@link User}
	 * @return A {@link Collection} of the {@link TeamSpace}s owned by the given {@link User}
	 */
	Collection<TeamSpace> getOwnedTeamSpaces(User user);

	/**
	 * Persists the given {@link TeamSpace}
	 * 
	 * @param teamSpace The {@link TeamSpace} to persist
	 */
	void persist(TeamSpace teamSpace);
	
	/**
	 * Persists the given {@link TeamSpaceMember}
	 * 
	 * @param tsMember The {@link TeamSpaceMember} to persist
	 */
	void persist(TeamSpaceMember tsMember);
	
	/**
	 * Persists the given {@link TeamSpaceApp}
	 * 
	 * @param teamSpaceApp The {@link TeamSpaceApp} to persist
	 */
	void persist(TeamSpaceApp teamSpaceApp);
	
	/**
	 * Merges the given {@link TeamSpace} with the representation in the database and returns
	 * the result.
	 * 
	 * @param teamSpace The {@link TeamSpace} with the new data
	 * @return The merged {@link TeamSpace}
	 */
	TeamSpace merge(TeamSpace teamSpace);
	
	/**
	 * Merges the given {@link TeamSpaceMember} with the representation in the database and returns
	 * the result.
	 * 
	 * @param member The {@link TeamSpaceMember} with the new data
	 * @return The merged {@link TeamSpaceMember}
	 */
	TeamSpaceMember merge(TeamSpaceMember member);
	
	/**
	 * Merges the given {@link TeamSpaceApp} with the representation in the database and returns
	 * the result.
	 * 
	 * @param app The {@link TeamSpaceApp} with the new data
	 * @return The merged {@link TeamSpaceApp}
	 */
	TeamSpaceApp merge(TeamSpaceApp app);
	
	/**
	 * Removes the given {@link TeamSpace} from the database
	 * 
	 * @param teamSpace The {@link TeamSpace} to be removed
	 */
	void remove(TeamSpace teamSpace);
	
	/**
	 * Removes the given {@link TeamSpaceMember} from the database
	 * 
	 * @param member The {@link TeamSpaceMember} to be removed
	 */
	void remove(TeamSpaceMember member);
	
	/**
	 * Removes the given {@link TeamSpaceApp} from the database
	 * 
	 * @param app The {@link TeamSpaceApp} to be removed
	 */
	void remove(TeamSpaceApp app);

	/**
	 * Tests if a {@link TeamSpaceApp} with the given id exists
	 * 
	 * @param appId The {@link TeamSpaceApp} id
	 * @return true if the {@link TeamSpaceApp} exists; false otherwise
	 */
	boolean appExists(String appId);

	/**
	 * Creates a new {@link TeamSpaceApp} instance of the given type
	 * 
	 * @param appId The type ({@link TeamSpaceApp} id)
	 * @return The newly created {@link TeamSpaceApp}
	 */
	TeamSpaceApp installTeamSpaceApp(TeamSpace teamSpace, String appId);

	/**
	 * Returns a {@link Collection} of all existing {@link TeamSpace}s
	 * 
	 * @return A {@link Collection} of {@link TeamSpace}s
	 */
	Collection<TeamSpace> getAllTeamSpaces();

	/**
	 * Tests if the current user is a global {@link TeamSpace} admin
	 * 
	 * @return true if the current user is a global {@link TeamSpace} admin; false otherwise
	 */
	boolean isGlobalTsAdmin();
	
	/**
	 * Tests if the given {@link User} is a global {@link TeamSpace} admin
	 * 
	 * @param user The {@link User}
	 * @return true if the given {@link User} is a global {@link TeamSpace} admin; false otherwise
	 */
	boolean isGlobalTsAdmin(User user);

	/**
	 * Tests if the current user is a manager of the given {@link TeamSpace}
	 * 
	 * @param teamSpace The {@link TeamSpace}
	 * @return true if the current user is a manager of the given {@link TeamSpace}; false otherwise
	 */
	boolean isManager(TeamSpace teamSpace);

	/**
	 * Tests if the given {@link User} is a manager of the given {@link TeamSpace}
	 * 
	 * @param user The {@link User}
	 * @param teamSpace The {@link TeamSpace}
	 * @return true if the given {@link User} is a manager of the given {@link TeamSpace}; false otherwise
	 */
	boolean isManager(User user, TeamSpace teamSpace);

	/**
	 * Tests if the current user is a guest in the given {@link TeamSpace}
	 * 
	 * @param teamSpace The {@link TeamSpace}
	 * @return true if the current user is a guest in the given {@link TeamSpace}; false otherwise
	 */
	boolean isGuest(TeamSpace teamSpace);

	/**
	 * Tests if the given {@link User} is a guest in the given {@link TeamSpace}
	 * 
	 * @param teamSpace The {@link TeamSpace}
	 * @return true if the given {@link User} is a guest in the given {@link TeamSpace}; false otherwise
	 */
	boolean isGuest(User folk, TeamSpace teamSpace);

	/**
	 * Tests if the current user is a user of the given {@link TeamSpace}
	 * 
	 * @param teamSpace The {@link TeamSpace}
	 * @return true if the current user is a user of the given {@link TeamSpace}; false otherwise
	 */
	boolean isUser(TeamSpace teamSpace);

	/**
	 * Tests if the given {@link User} is a user of the given {@link TeamSpace}
	 * 
	 * @param teamSpace The {@link TeamSpace}
	 * @return true if the given {@link User} is a user of the given {@link TeamSpace}; false otherwise
	 */
	boolean isUser(User folk, TeamSpace teamSpace);

	/**
	 * Tests if the current user is an admin of the given {@link TeamSpace}
	 * 
	 * @param teamSpace The {@link TeamSpace}
	 * @return true if the current user is an admin of the given {@link TeamSpace}; false otherwise
	 */
	boolean isAdmin(TeamSpace teamSpace);

	/**
	 * Tests if the given {@link User} is an admin of the given {@link TeamSpace}
	 * 
	 * @param user The {@link User}
	 * @param teamSpace The {@link TeamSpace}
	 * @return true if the given {@link User} is an admin of the given {@link TeamSpace}; false otherwise
	 */
	boolean isAdmin(User user, TeamSpace teamSpace);

	/**
	 * Tests if the given {@link User} has the given {@link TeamSpaceRole} on the given {@link TeamSpace}
	 * 
	 * @param user The {@link User}
	 * @param teamSpace The {@link TeamSpace}
	 * @param roleToHave The {@link TeamSpaceRole} to test
	 * @return true if the {@link User} has the {@link TeamSpaceRole}; false otherwise
	 */
	boolean hasRole(User user, TeamSpace teamSpace, TeamSpaceRole roleToHave);

	/**
	 * Tests if the current user has the given {@link TeamSpaceRole} on the given {@link TeamSpace}
	 * 
	 * @param teamSpace The {@link TeamSpace}
	 * @param roleToHave The {@link TeamSpaceRole} to test
	 * @return true if the current user has the {@link TeamSpaceRole}; false otherwise
	 */
	boolean hasRole(TeamSpace teamSpace, TeamSpaceRole roleToHave);

	/**
	 * Tests if the current user has the right to access the given {@link TeamSpace}
	 * 
	 * @param teamSpace The {@link TeamSpace}
	 * @return true if the current user has the right to access the given {@link TeamSpace}; false otherwise
	 */
	public boolean mayAccess(TeamSpace teamSpace);

	/**
	 * Tests if the given {@link User} has the right to access the given {@link TeamSpace}
	 * 
	 * @param user The {@link User}
	 * @param teamSpace The {@link TeamSpace}
	 * @return true if the {@link User} has the right to access the given {@link TeamSpace}; false otherwise
	 */
	boolean mayAccess(User user, TeamSpace teamSpace);

	TeamSpace getTeamSpaceByName(String name);
	
	Collection<TeamSpace> getTeamSpacesByName(String name);

	void provideAccess(TeamSpace teamSpace, User user, TeamSpaceRole role);

	void setInstalledApps(TeamSpace teamSpace, Collection<String> appIds);

	TeamSpaceAppDefinition getAppDefinitionById(String appId);

	TeamSpaceMember getMemberFor(TeamSpace teamSpace, AbstractUserManagerNode folk);

	void forceRemove(TeamSpace teamSpace);

	TeamSpaceRole getRole(TeamSpace teamSpace);

	TeamSpaceRole getRole(User user, TeamSpace teamSpace);

	/**
	 * checks if the current user owns the TeamSpace
	 * 
	 * @param teamSpace
	 */
	boolean isOwner(TeamSpace teamSpace);
	
	Collection<TeamSpace> getTeamSpacesWithMemberFor(AbstractUserManagerNode folk);
	
	void assertAccess(TeamSpace ts);

	void assertIsUser(TeamSpace teamSpace);

}
