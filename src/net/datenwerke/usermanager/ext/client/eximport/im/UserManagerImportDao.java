package net.datenwerke.usermanager.ext.client.eximport.im;

import java.util.List;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;
import net.datenwerke.usermanager.ext.client.eximport.im.rpc.UserManagerImportRpcServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;


public class UserManagerImportDao extends Dao {

	private final UserManagerImportRpcServiceAsync rpcService;

	@Inject
	public UserManagerImportDao(UserManagerImportRpcServiceAsync rpcService) {
		this.rpcService = rpcService;
	}
	
	public void loadTree(AsyncCallback<List<ImportTreeModel>> callback){
		rpcService.loadTree(transformAndKeepCallback(callback));
	}
	
}
