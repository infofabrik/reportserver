package net.datenwerke.rs.core.client.reportexporter.rpc;

import java.util.List;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public interface ReportExporterRpcServiceAsync {

   Request storeInSessionForExport(ReportDto reportDto, String executorToken, String format,
         List<ReportExecutionConfigDto> configs, AsyncCallback<Void> callback);

   void getExportDefaultSettingsAsXml(String identifier, AsyncCallback<String> callback);

   void getExportDefaultCharset(AsyncCallback<String> callback);

   Request exportSkipDownload(ReportDto reportDto, String executorToken, String format, AsyncCallback<Void> callback);
}
