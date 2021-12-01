package net.datenwerke.rs.base.ext.client.dashboardmanager.eximport.im;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.base.ext.client.dashboardmanager.eximport.im.rpc.DashboardManagerImportRpcServiceAsync;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;


public class DashboardManagerImportDao extends Dao {

	private final DashboardManagerImportRpcServiceAsync rpcService;

	@Inject
	public DashboardManagerImportDao(DashboardManagerImportRpcServiceAsync rpcService) {
		this.rpcService = rpcService;
	}
	
	public void loadTree(AsyncCallback<List<ImportTreeModel>> callback){
		rpcService.loadTree(transformAndKeepCallback(callback));
	}
	
}
