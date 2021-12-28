package net.datenwerke.rs.uservariables.client.uservariables.rpc;

import java.util.Collection;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.ListLoadResult;

import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableInstanceDto;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;

public interface UserVariablesRpcServiceAsync {

   void addUserVariableDefinition(UserVariableDefinitionDto definition,
         AsyncCallback<UserVariableDefinitionDto> callback);

   void addUserVariableInstances(List<UserVariableDefinitionDto> definitionDto, AbstractUserManagerNodeDto nodeDto,
         AsyncCallback<List<UserVariableInstanceDto>> callback);

   void getDefinedUserVariableDefinitions(AsyncCallback<ListLoadResult<UserVariableDefinitionDto>> callback);

   void getDefinedUserVariableInstances(AbstractUserManagerNodeDto nodeDto,
         AsyncCallback<List<UserVariableInstanceDto>> callback);

   void getInheritedUserVariableInstances(AbstractUserManagerNodeDto nodeDto,
         AsyncCallback<List<UserVariableInstanceDto>> callback);

   void removeUserVariableDefinitions(Collection<UserVariableDefinitionDto> definition, boolean force,
         AsyncCallback<Void> callback);

   void removeUserVariableInstances(Collection<UserVariableInstanceDto> instanceDto, AsyncCallback<Void> callback);

   void updateUserVariableDefinition(UserVariableDefinitionDto definition,
         AsyncCallback<UserVariableDefinitionDto> callback);

   void updateUserVariableInstance(UserVariableInstanceDto instanceDto,
         AsyncCallback<UserVariableInstanceDto> callback);

}
