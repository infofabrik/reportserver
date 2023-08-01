package net.datenwerke.rs.core.client.reportexecutor.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gf.client.history.HistoryLocation;
import net.datenwerke.gxtdto.client.model.DwModel;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

@RemoteServiceRelativePath("executor")
public interface ReportExecutorRpcService extends RemoteService {

   DwModel executeAs(String format, String executeToken, ReportDto report, DwModel config)
         throws ServerCallFailedException;

   DwModel storePNGInSession(String executorToken, ReportDto report) throws ServerCallFailedException;

   ReportDto createNewVariant(ReportDto reportVariantDto, String executeToken, String title, String description)
         throws ServerCallFailedException;

   ReportDto editVariant(ReportDto reportVariantDto, String executeToken, String title, String description)
         throws ServerCallFailedException;

   void deleteVariant(ReportDto reportVariantDto) throws ServerCallFailedException;

   ReportDto loadFullReportForExecution(ReportDto report) throws ServerCallFailedException;

   ReportDto loadReportForExecutionFrom(HistoryLocation location) throws ServerCallFailedException;

   String getPreviewMode() throws ServerCallFailedException;

   void setPreviewModeUserProperty(String value) throws ServerCallFailedException;

   ReportDto loadFullReportUnmanaged(ReportDto report);

   Integer getDefaultColumnWidth() throws ServerCallFailedException;

   Integer getMaxColumnWidth() throws ServerCallFailedException;
   
   Integer getWarnRecordExportThreshold() throws ServerCallFailedException;

}
