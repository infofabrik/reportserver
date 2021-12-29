package net.datenwerke.rs.core.client.reportexecutor.ui.preview;

import java.util.List;

import javax.inject.Inject;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.MarginData;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwBorderContainer;
import net.datenwerke.gxtdto.client.model.DwModel;
import net.datenwerke.gxtdto.client.model.SuccessIndicatorBaseModel;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorDao;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterUIService;
import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporter;
import net.datenwerke.rs.core.client.reportexporter.exporter.generic.Export2PDF;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public class JsViewerReportPreviewView extends AbstractReportPreviewView {

   private DwBorderContainer wrapper;
   private ReportExporterUIService exporterUIService;

   @Inject
   public JsViewerReportPreviewView(ReportExecutorDao rexService, HookHandlerService hookHandler,
         ReportExporterUIService exporterUIService) {

      super(rexService, hookHandler);
      this.exporterUIService = exporterUIService;

      wrapper = new DwBorderContainer();
      wrapper.setBorders(false);
   }

   @Override
   protected void doLoadReport(DwModel reportExecutionResult) {
      if (((SuccessIndicatorBaseModel) reportExecutionResult).isSuccess()) {
         wrapper.clear();

         DwContentPanel frame = new DwContentPanel();
         frame.setBodyBorder(false);
         frame.setHeaderVisible(false);
         Frame iFrame = frame.setUrl("resources/js/pdf.js-2.12.313/web/viewer.html?file=" + URL.encodeQueryString(
               exporterUIService.getExportServletPath() + "&tid=" + getExecuteReportToken() + "&isJsViewer=true"));
         iFrame.getElement().setPropertyString("scrolling", "no");
         iFrame.getElement().addClassName("rs-report-preview");

         wrapper.add(frame, new MarginData(0));
      }
   }

   @Override
   protected void cancelExecution(String executeToken) {
      // TODO Auto-generated method stub

   }

   @Override
   public Request execute(ReportDto report, String executeToken, final AsyncCallback<DwModel> callback) {
      /* Find exporter */
      ReportExporter exporter = null;
      List<ReportExporter> exporters = exporterUIService.getAvailableExporters(report);
      for (ReportExporter rex : exporters) {
         if (rex instanceof Export2PDF) {
            exporter = rex;
            break;
         }
      }

      if (null == exporter)
         throw new RuntimeException("No PDFExporter for " + report.getClass().getName());

      return exporter.prepareExportForPreview(report, getExecuteReportToken(), new AsyncCallback<Void>() {
         @Override
         public void onFailure(Throwable caught) {
            callback.onFailure(caught);
         }

         @Override
         public void onSuccess(Void result) {
            SuccessIndicatorBaseModel model = new SuccessIndicatorBaseModel();
            model.setSuccess(true);
            callback.onSuccess(model);
         }
      });
   }

   @Override
   public Widget doGetViewComponent() {
      return wrapper;
   }

   @Override
   protected boolean isCreateStatusBar() {
      return false;
   }
}
