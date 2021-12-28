package net.datenwerke.rs.base.ext.client.reportmanager.eximport.ex;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.base.ext.client.reportmanager.eximport.ex.rpc.ReportManagerExportRpcServiceAsync;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto;

public class ReportManagerExportDao extends Dao  {

	private final ReportManagerExportRpcServiceAsync rpcService;

	@Inject
	public ReportManagerExportDao(ReportManagerExportRpcServiceAsync rpcService) {
		this.rpcService = rpcService;
	}
	
	public void quickExport(AbstractReportManagerNodeDto dto, boolean includeVariants,
			AsyncCallback<Void> callback){
		rpcService.quickExport(dto, includeVariants, transformAndKeepCallback(callback));
	}
	
	public void loadResult(AsyncCallback<String> callback){
		rpcService.loadResult(transformAndKeepCallback(callback));
	}
	
}
