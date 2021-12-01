package net.datenwerke.rs.base.ext.client.datasourcemanager.eximport.im;

import java.util.List;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.base.ext.client.datasourcemanager.eximport.im.rpc.DatasourceManagerImportRpcServiceAsync;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class DatasourceManagerImportDao extends Dao {

	private final DatasourceManagerImportRpcServiceAsync rpcService;

	@Inject
	public DatasourceManagerImportDao(DatasourceManagerImportRpcServiceAsync rpcService) {
		this.rpcService = rpcService;
	}
	
	public void loadTree(AsyncCallback<List<ImportTreeModel>> callback){
		rpcService.loadTree(transformAndKeepCallback(callback));
	}
	
}
