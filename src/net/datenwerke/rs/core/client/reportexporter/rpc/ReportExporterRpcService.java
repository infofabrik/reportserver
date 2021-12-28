package net.datenwerke.rs.core.client.reportexporter.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;

@RemoteServiceRelativePath("reportexporterrpc")
public interface ReportExporterRpcService extends RemoteService {

	public void storeInSessionForExport(ReportDto reportDto, String executorToken, String format, List<ReportExecutionConfigDto> configs) throws ServerCallFailedException;
	
	void exportViaMail(ReportDto reportDto, String executorToke, String format,
			List<ReportExecutionConfigDto> configs, String subject, String message, boolean compressed, List<StrippedDownUser> recipients) throws ServerCallFailedException, ExpectedException;
	
	public String getExportDefaultSettingsAsXml(String identifier);
	
	public String getExportDefaultCharset();
	
	void exportSkipDownload(ReportDto reportDto, String executorToken, String format) throws ServerCallFailedException;
}
