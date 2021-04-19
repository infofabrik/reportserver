package net.datenwerke.usermanager.ext.client.properties;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.UserPropertyDto;
import net.datenwerke.usermanager.ext.client.properties.rpc.UserPropertiesRpcServiceAsync;

public class UserPropertiesDao extends Dao{

	private final UserPropertiesRpcServiceAsync rpcService;
	
	@Inject
	public UserPropertiesDao(
			UserPropertiesRpcServiceAsync rpcService	
		){
	
		/* store objects */
		this.rpcService = rpcService;
	}
	
	public void getPropertyKeys(AsyncCallback<List<String>> callback){
		rpcService.getPropertyKeys(transformAndKeepCallback(callback));
	}

	public void updateProperties(UserDto user, List<UserPropertyDto> addedProperties,
			List<UserPropertyDto> modifiedProperties, List<UserPropertyDto> removedProperties,
			AsyncCallback<UserDto> callback) {
		rpcService.updateProperties(user, addedProperties, modifiedProperties, removedProperties, transformDtoCallback(callback));
	}
}
