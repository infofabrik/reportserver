package net.datenwerke.rs.passwordpolicy.client.lostpassword;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.security.client.security.lostpassword.rpc.LostPasswordRpcServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class LostPasswordDao extends Dao {

	private final LostPasswordRpcServiceAsync rpcService;

	@Inject
	public LostPasswordDao(LostPasswordRpcServiceAsync rpcService) {
		this.rpcService = rpcService;
	}
	
	public void requestNewPassword(String username, AsyncCallback<String> callback){
		rpcService.requestNewPassword(username, transformAndKeepCallback(callback));
	}
	
}
