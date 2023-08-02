package net.datenwerke.rs.core.client.reportexecutor.rpc;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.gf.client.history.HistoryLocation;
import net.datenwerke.gxtdto.client.model.DwModel;
import net.datenwerke.rs.core.client.reportexecutor.dto.ExportThresholdDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public interface ReportExecutorRpcServiceAsync {

   void createNewVariant(ReportDto reportVariantDto, String executeToken, String title, String description,
         AsyncCallback<ReportDto> callback);

   void editVariant(ReportDto reportVariantDto, String executeToken, String title, String description,
         AsyncCallback<ReportDto> callback);

   void deleteVariant(ReportDto reportVariantDto, AsyncCallback<Void> callback);

   Request storePNGInSession(String executorToken, ReportDto report, AsyncCallback<DwModel> callback);

   Request executeAs(String format, String executeToken, ReportDto report, DwModel config,
         AsyncCallback<DwModel> callback);

   void loadFullReportForExecution(ReportDto report, AsyncCallback<ReportDto> callback);

   void loadReportForExecutionFrom(HistoryLocation location, AsyncCallback<ReportDto> callback);

   void getPreviewMode(AsyncCallback<String> callback);

   void setPreviewModeUserProperty(String value, AsyncCallback<Void> callback);

   void loadFullReportUnmanaged(ReportDto report, AsyncCallback<ReportDto> callback);

   void getDefaultColumnWidth(AsyncCallback<Integer> callback);

   void getMaxColumnWidth(AsyncCallback<Integer> callback);
   
   void getWarnRecordExportThreshold(AsyncCallback<ExportThresholdDto> callback);

}
