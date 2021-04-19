package net.datenwerke.rs.uservariables.client.uservariables;

import java.util.Collection;
import java.util.List;

import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.DaoAsyncCallback;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.NeedForcefulDeleteClientException;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableInstanceDto;
import net.datenwerke.rs.uservariables.client.uservariables.rpc.UserVariablesRpcServiceAsync;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;

public class UserVariableDao extends Dao {

	private final UserVariablesRpcServiceAsync rpcService;

	@Inject
	public UserVariableDao(UserVariablesRpcServiceAsync rpcService) {
		super();
		this.rpcService = rpcService;
	}
	
	
	public void addUserVariableDefinition(UserVariableDefinitionDto definition,
			AsyncCallback<UserVariableDefinitionDto> callback){
		rpcService.addUserVariableDefinition(definition, transformDtoCallback(callback));
	}

	public void addUserVariableInstances(List<UserVariableDefinitionDto> definitionDto,
			AbstractUserManagerNodeDto nodeDto,
			AsyncCallback<List<UserVariableInstanceDto>> callback){
		rpcService.addUserVariableInstances(definitionDto, nodeDto, transformListCallback(callback));
	}

	public void getDefinedUserVariableDefinitions(
			AsyncCallback<ListLoadResult<UserVariableDefinitionDto>> callback){
		rpcService.getDefinedUserVariableDefinitions(transformListLoadCallback(callback));
	}

	public void getDefinedUserVariableInstances(AbstractUserManagerNodeDto nodeDto,
			AsyncCallback<List<UserVariableInstanceDto>> callback){
		rpcService.getDefinedUserVariableInstances(nodeDto, transformListCallback(callback));
	}

	public void getInheritedUserVariableInstances(AbstractUserManagerNodeDto nodeDto,
			AsyncCallback<List<UserVariableInstanceDto>> callback){
		rpcService.getInheritedUserVariableInstances(nodeDto, transformListCallback(callback));
	}
	
	public void removeUserVariableAndAskForForce(final Collection<UserVariableDefinitionDto> definition, final AsyncCallback<Void> callback){
		DaoAsyncCallback<Void> daoCallback = transformAndKeepCallback(callback);
		daoCallback.addDtosToDetach(definition);
		daoCallback.ignoreExpectedExceptions(true);
		rpcService.removeUserVariableDefinitions(definition, false, new RsAsyncCallback<Void>(){
			@Override
			public void onSuccess(Void result) {
				callback.onSuccess(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				if(caught instanceof NeedForcefulDeleteClientException){
					ConfirmMessageBox cmb = new DwConfirmMessageBox(BaseMessages.INSTANCE.needForcefulDeleteTitle(), BaseMessages.INSTANCE.needForcefulDeleteMsg(caught.getMessage()));
					cmb.addDialogHideHandler(new DialogHideHandler() {
						@Override
						public void onDialogHide(DialogHideEvent event) {
							if (event.getHideButton() == PredefinedButton.YES) 
								rpcService.removeUserVariableDefinitions(definition, true, callback);	
						}
					});
					cmb.show();
				} else 
					callback.onFailure(caught);
			}
		});
	}
	
	public void removeUserVariableInstances(
			Collection<UserVariableInstanceDto> instanceDto,
			AsyncCallback<Void> callback){
		rpcService.removeUserVariableInstances(instanceDto, transformAndKeepCallback(callback));
	}

	public void updateUserVariableDefinition(UserVariableDefinitionDto definition,
			AsyncCallback<UserVariableDefinitionDto> callback){
		rpcService.updateUserVariableDefinition(definition, transformDtoCallback(callback));
	}

	public void updateUserVariableInstance(UserVariableInstanceDto instanceDto,
			AsyncCallback<UserVariableInstanceDto> callback){
		rpcService.updateUserVariableInstance(instanceDto, transformDtoCallback(callback));
	}
}
