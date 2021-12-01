package net.datenwerke.usermanager.ext.client.eximport.ex;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;
import net.datenwerke.usermanager.ext.client.eximport.ex.rpc.UserManagerExportRpcServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;


public class UserManagerExportDao extends Dao {

	private final UserManagerExportRpcServiceAsync rpcService;

	@Inject
	public UserManagerExportDao(UserManagerExportRpcServiceAsync rpcService) {
		this.rpcService = rpcService;
	}
	
	public void quickExport(AbstractUserManagerNodeDto dto, AsyncCallback<Void> callback){
		rpcService.quickExport(dto, transformAndKeepCallback(callback));
	}
	
	public void loadResult(AsyncCallback<String> callback){
		rpcService.loadResult(transformAndKeepCallback(callback));
	}
}
