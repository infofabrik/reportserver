package net.datenwerke.rs.base.ext.client.reportmanager.eximport.im;

import java.util.List;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.base.ext.client.reportmanager.eximport.im.rpc.ReportManagerImportRpcServiceAsync;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class ReportManagerImportDao extends Dao {

	private final ReportManagerImportRpcServiceAsync rpcService;

	@Inject
	public ReportManagerImportDao(ReportManagerImportRpcServiceAsync rpcService) {
		this.rpcService = rpcService;
	}
	
	public void loadTree(AsyncCallback<List<ImportTreeModel>> callback){
		rpcService.loadTree(transformAndKeepCallback(callback));
	}
	
}
