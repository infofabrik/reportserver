package net.datenwerke.rs.uservariables.client.uservariables.rpc;

import java.util.Collection;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.ListLoadResult;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableInstanceDto;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;

/**
 * 
 *
 */
@RemoteServiceRelativePath("uservariables")
public interface UserVariablesRpcService extends RemoteService {

   public List<UserVariableInstanceDto> addUserVariableInstances(List<UserVariableDefinitionDto> definitionDto,
         AbstractUserManagerNodeDto nodeDto) throws ServerCallFailedException;

   public UserVariableInstanceDto updateUserVariableInstance(UserVariableInstanceDto instanceDto)
         throws ServerCallFailedException;

   public void removeUserVariableInstances(Collection<UserVariableInstanceDto> instanceDto)
         throws ServerCallFailedException;

   /**
    * Adds a new user variable to the list of available variables.
    * 
    * @param definition
    * @throws ServerCallFailedException
    */
   public UserVariableDefinitionDto addUserVariableDefinition(UserVariableDefinitionDto definition)
         throws ServerCallFailedException;

   /**
    * Changes the definition of a user variable in the list of available variables.
    * 
    * @param definition
    * @throws ServerCallFailedException
    */
   public UserVariableDefinitionDto updateUserVariableDefinition(UserVariableDefinitionDto definition)
         throws ServerCallFailedException;

   void removeUserVariableDefinitions(Collection<UserVariableDefinitionDto> definition, boolean force)
         throws ServerCallFailedException;

   /**
    * Returns all defined variables.
    * 
    * @throws ServerCallFailedException
    */
   public ListLoadResult<UserVariableDefinitionDto> getDefinedUserVariableDefinitions()
         throws ServerCallFailedException;

   /**
    * Returns the instances for a given node.
    * 
    * @param nodeDto
    * @throws ServerCallFailedException
    */
   public List<UserVariableInstanceDto> getDefinedUserVariableInstances(AbstractUserManagerNodeDto nodeDto)
         throws ServerCallFailedException;

   /**
    * Returns the inherited instances for a given node.
    * 
    * @param nodeDto
    * @throws ServerCallFailedException
    */
   public List<UserVariableInstanceDto> getInheritedUserVariableInstances(AbstractUserManagerNodeDto nodeDto)
         throws ServerCallFailedException;
}
