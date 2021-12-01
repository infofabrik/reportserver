package net.datenwerke.rs.tabletemplate.client.tabletemplate.rpc;

import java.util.Collection;
import java.util.List;

import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TableTemplateRpcServiceAsync {

	void removeTemplates(ReportDto report, String executeToken, Collection<TableReportTemplateDto> templates, AsyncCallback<Void> callback);

	void loadTemplates(ReportDto report, String executeToken,
			AsyncCallback<List<TableReportTemplateDto>> callback);


}
