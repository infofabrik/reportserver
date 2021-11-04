package net.datenwerke.rs.core.client.reportexporter.rpc;

import java.util.List;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;

public interface ReportExporterRpcServiceAsync {

	Request storeInSessionForExport(ReportDto reportDto, String executorToken,
			String format, List<ReportExecutionConfigDto> configs,
			AsyncCallback<Void> callback);

	void exportViaMail(ReportDto reportDto, String executorToke, String format,
			List<ReportExecutionConfigDto> configs, String subject,
			String message, boolean compressed, List<StrippedDownUser> recipients,
			AsyncCallback<Void> callback);
	
	void getExportDefaultSettingsAsJSON(String identifier, AsyncCallback<String> callback);
	
	void getExportDefaultCharset(AsyncCallback<String> callback);

	Request exportSkipDownload(ReportDto reportDto, String executorToken, String format, AsyncCallback<Void> callback);
}
