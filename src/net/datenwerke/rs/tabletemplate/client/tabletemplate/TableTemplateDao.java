package net.datenwerke.rs.tabletemplate.client.tabletemplate;

import java.util.Collection;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateDto;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.rpc.TableTemplateRpcServiceAsync;

public class TableTemplateDao extends Dao {

	private final TableTemplateRpcServiceAsync rpcService;

	@Inject
	public TableTemplateDao(TableTemplateRpcServiceAsync rpcService) {
		this.rpcService = rpcService;
	}
	
	public void removeTemplates(ReportDto report, String executeToken, Collection<TableReportTemplateDto> templates, AsyncCallback<Void> callback){
		rpcService.removeTemplates(report, executeToken, templates, transformAndKeepCallback(callback));
	}
	
	public void loadTemplates(ReportDto report, String executeToken,
			AsyncCallback<List<TableReportTemplateDto>> callback){
		rpcService.loadTemplates(report, executeToken, transformAndKeepCallback(callback));
	}
	
}
