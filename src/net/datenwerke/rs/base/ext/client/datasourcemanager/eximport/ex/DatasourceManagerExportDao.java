package net.datenwerke.rs.base.ext.client.datasourcemanager.eximport.ex;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.base.ext.client.datasourcemanager.eximport.ex.rpc.DatasourceManagerExportRpcServiceAsync;
import net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto;

public class DatasourceManagerExportDao extends Dao {

	private final DatasourceManagerExportRpcServiceAsync rpcService;

	@Inject
	public DatasourceManagerExportDao(DatasourceManagerExportRpcServiceAsync rpcService) {
		this.rpcService = rpcService;
	}
	
	public void quickExport(AbstractDatasourceManagerNodeDto dto, AsyncCallback<Void> callback){
		rpcService.quickExport(dto, transformAndKeepCallback(callback));
	}
	
	public void loadResult(AsyncCallback<String> callback){
		rpcService.loadResult(transformAndKeepCallback(callback));
	}
	
}
