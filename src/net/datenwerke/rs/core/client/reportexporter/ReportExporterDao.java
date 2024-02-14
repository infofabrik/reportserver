package net.datenwerke.rs.core.client.reportexporter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportexporter.rpc.ReportExporterRpcServiceAsync;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public class ReportExporterDao extends Dao {

   private final ReportExporterRpcServiceAsync rpcService;

   @Inject
   public ReportExporterDao(ReportExporterRpcServiceAsync rpcService) {
      super();
      this.rpcService = rpcService;
   }

   public Request storeInSessionForExport(ReportDto reportDto, String executorToken, String format,
         AsyncCallback<Void> callback) {
      List<ReportExecutionConfigDto> configs = new ArrayList<ReportExecutionConfigDto>();
      return storeInSessionForExport(reportDto, executorToken, format, configs, callback);
   }

   public Request storeInSessionForExport(ReportDto reportDto, String executorToken, String format,
         ReportExecutionConfigDto config, AsyncCallback<Void> callback) {
      List<ReportExecutionConfigDto> configs = new ArrayList<ReportExecutionConfigDto>();
      configs.add(config);
      return storeInSessionForExport(reportDto, executorToken, format, configs, callback);
   }

   public Request storeInSessionForExport(ReportDto reportDto, String executorToken, String format,
         List<ReportExecutionConfigDto> configs, AsyncCallback<Void> callback) {
      reportDto = unproxy(reportDto);
      return rpcService.storeInSessionForExport(reportDto, executorToken, format, configs,
            transformAndKeepCallback(callback));
   }

   public void getExportDefaultSettingsAsXml(String identifier, AsyncCallback<String> callback) {
      rpcService.getExportDefaultSettingsAsXml(identifier, transformAndKeepCallback(callback));
   }

   public void getExportDefaultCharset(AsyncCallback<String> callback) {
      rpcService.getExportDefaultCharset(transformAndKeepCallback(callback));
   }

   public Request exportSkipDownload(ReportDto reportDto, String executorToken, String format,
         AsyncCallback<Void> callback) {
      return rpcService.exportSkipDownload(reportDto, executorToken, format, transformAndKeepCallback(callback));
   }
}
