package net.datenwerke.rs.uservariables.service.uservariables;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import net.datenwerke.rs.uservariables.service.parameters.UserVariableParameterDefinition;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableDefinition;
import net.datenwerke.rs.uservariables.service.uservariables.entities.UserVariableInstance;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.User;

public interface UserVariableService {

	public Collection<UserVariableInstance> getInstancesForDefinition(UserVariableDefinition def);
	
	/**
	 * Returns a {@link Set} of all available {@link UserVariableDefinition}s
	 * 
	 * @return A {@link Set} of {@link UserVariableDefinition}s
	 */
	public Set<UserVariableDefinition> getAvailableUserVariableDefinitionTypes();
	
	/**
	 * Returns a {@link Collection} of all defined {@link UserVariableDefinition}s
	 * 
	 * @return A {@link Collection} of {@link UserVariableDefinition}s
	 */
	public Collection<UserVariableDefinition> getDefinedVariableDefinitions();
	
	/**
	 * Tests if the given {@link AbstractUserManagerNode} has an instance of the
	 * {@link UserVariableDefinition}
	 * 
	 * @param folk The {@link AbstractUserManagerNode}
	 * @param definition The {@link UserVariableDefinition}
	 * @return true if the test passed; false otherwise
	 */
	public boolean hasVariableInstance(AbstractUserManagerNode folk, UserVariableDefinition definition);
	
	/**
	 * Returns a {@link Collection} of all {@link UserVariableInstance}s defined by the given
	 * {@link AbstractUserManagerNode}
	 * 
	 * @param folk The {@link AbstractUserManagerNode}
	 * @return A {@link Collection} of {@link UserVariableInstance}s
	 */
	public Collection<UserVariableInstance> getDefinedInstancesFor(AbstractUserManagerNode folk);
	
	/**
	 * Returns a {@link List} of all {@link UserVariableInstance}s which are inherited
	 * from the parents of the given {@link AbstractUserManagerNode}
	 * 
	 * @param folk The {@link AbstractUserManagerNode}
	 * @return A {@link List} of {@link UserVariableInstance}s
	 */
	public List<UserVariableInstance> getInheritedInstancesFor(AbstractUserManagerNode folk);
	
	/**
	 * Returns the {@link UserVariableDefinition} identified by the given <i>id</i>
	 * 
	 * @param id The <i>id</i>
	 * @return The corresponding {@link UserVariableDefinition}
	 */
	public UserVariableDefinition getUserVariableDefinitionById(long id);
	
	/**
	 * Persists the submitted {@link UserVariableDefinition}
	 * 
	 * @param definition The {@link UserVariableDefinition}
	 */
	public void persist(UserVariableDefinition definition);
	
	/**
	 * Merges the submitted {@link UserVariableDefinition} with the representation in
	 * the database and returns the result.
	 * 
	 * @param definition The {@link UserVariableDefinition} with the new data
	 * @return The merged {@link UserVariableDefinition}
	 */
	public UserVariableDefinition merge(UserVariableDefinition definition);
	
	/**
	 * Removes the submitted {@link UserVariableDefinition} from the database.
	 * 
	 * @param definition The {@link UserVariableDefinition} to be removed
	 */
	public void remove(UserVariableDefinition definition);
	
	/**
	 * Persists the submitted {@link UserVariableInstance}
	 * 
	 * @param instance The {@link UserVariableInstance}
	 */
	public void persist(UserVariableInstance instance);
	
	/**
	 * Merges the submitted {@link UserVariableInstance} with the representation in the
	 * database and returns the merged result.
	 * 
	 * @param instance The {@link UserVariableInstance} with the new data
	 * @return The merged {@link UserVariableInstance}
	 */
	public UserVariableInstance merge(UserVariableInstance instance);
	
	/**
	 * Removes the submitted {@link UserVariableInstance} from the database
	 * 
	 * @param instance The {@link UserVariableInstance} to be removed
	 */
	public void remove(UserVariableInstance instance);

	void forceRemove(UserVariableDefinition definition);
	
	/**
	 * Returns the {@link UserVariableInstance} identified by the given <i>id</i>
	 * 
	 * @param id The <i>id</i>
	 * @return The corresponding {@link UserVariableInstance}
	 */
	public UserVariableInstance getUserVariableInstanceById(long id);

	/**
	 * Returns the {@link UserVariableInstance} which was instantiated from the given
	 * {@link UserVariableDefinition} by the current user
	 * 
	 * @param variableDefinition The {@link UserVariableDefinition}
	 * @return The corresponding {@link UserVariableInstance} or <b>null</b> if no
	 * 		   such {@link UserVariableInstance} could be found
	 */
	public UserVariableInstance getVariableInstanceForCurrentUser(UserVariableDefinition variableDefinition);
	
	/**
	 * Returns the {@link UserVariableInstance} which was instantiated from the given
	 * {@link UserVariableDefinition} by the given {@link User}
	 * 
	 * @param user The {@link User}
	 * @param variableDefinition The {@link UserVariableDefinition}
	 * @return The corresponding {@link UserVariableInstance} or <b>null</b> if no
	 * 		   such {@link UserVariableInstance} could be found
	 */
	public UserVariableInstance getVariableInstanceForUser(User user, UserVariableDefinition variableDefinition);

	void forceRemove(UserVariableInstance instance);

	Collection<UserVariableParameterDefinition> getParametersFor(
			UserVariableDefinition uvd);


}
