package net.datenwerke.rs.base.ext.client.dashboardmanager.eximport.ex;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.base.ext.client.dashboardmanager.eximport.ex.rpc.DashboardManagerExportRpcServiceAsync;
import net.datenwerke.rs.dashboard.client.dashboard.dto.AbstractDashboardManagerNodeDto;

public class DashboardManagerExportDao extends Dao {

	private final DashboardManagerExportRpcServiceAsync rpcService;

	@Inject
	public DashboardManagerExportDao(DashboardManagerExportRpcServiceAsync rpcService) {
		this.rpcService = rpcService;
	}
	
	public void quickExport(AbstractDashboardManagerNodeDto dto, AsyncCallback<Void> callback){
		rpcService.quickExport(dto, transformAndKeepCallback(callback));
	}
	
	public void loadResult(AsyncCallback<String> callback){
		rpcService.loadResult(transformAndKeepCallback(callback));
	}
	
}
