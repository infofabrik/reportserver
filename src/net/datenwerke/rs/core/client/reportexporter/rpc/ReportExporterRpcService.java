package net.datenwerke.rs.core.client.reportexporter.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

@RemoteServiceRelativePath("reportexporterrpc")
public interface ReportExporterRpcService extends RemoteService {

   public void storeInSessionForExport(ReportDto reportDto, String executorToken, String format,
         List<ReportExecutionConfigDto> configs) throws ServerCallFailedException;

   public String getExportDefaultSettingsAsXml(String identifier);

   public String getExportDefaultCharset();

   void exportSkipDownload(ReportDto reportDto, String executorToken, String format) throws ServerCallFailedException;
}
