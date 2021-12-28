package net.datenwerke.rs.passwordpolicy.client.activateuser.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.security.client.usermanager.dto.UserDto;

public class ActivateUserServiceDao extends Dao{
	
	private ActivateUserRpcServiceAsync rpcService;
	
	@Inject
	public ActivateUserServiceDao(ActivateUserRpcServiceAsync rpcService) {
		this.rpcService = rpcService;
	}
	
	public void activateAccount(UserDto user, boolean force, AsyncCallback<Void> callback){
		rpcService.activateAccount(user, force, transformAndKeepCallback(callback));
	}


}
