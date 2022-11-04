package net.datenwerke.rs.legacysaiku.client.saiku.ui;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.model.DwModel;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorDao;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.AbstractReportPreviewView;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.saiku.client.saiku.SaikuDao;
import net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto;

public class SaikuReportPreviewView extends AbstractReportPreviewView {

   private final UtilsUIService utilsService;
   private DwContentPanel wrapper;
   private SaikuDao saikuDao;

   @Inject
   public SaikuReportPreviewView(ReportExecutorDao rexService, SaikuDao saikuDao, HookHandlerService hookHandler,
         UtilsUIService utilsService) {
      super(rexService, hookHandler);

      this.saikuDao = saikuDao;
      this.utilsService = utilsService;

      wrapper = DwContentPanel.newInlineInstance();
   }

   @Override
   protected boolean isCreateStatusBar() {
      return false;
   }

   @Override
   protected void doLoadReport(DwModel reportExecutionResult) {
      wrapper.clear();
      VerticalLayoutContainer container = new VerticalLayoutContainer();
      wrapper.setWidget(container);

      boolean isConfigurationProtected = report.isConfigurationProtected();

      final Frame iframe = new Frame("resources/legacysaiku/index.html?username=" + getExecuteReportToken()
            + "&password=none&hideparents=" + ((SaikuReportDto) report).isHideParents()
            + "&RS_SHOW_RESET=true&RS_CONFIGURATION_PROTECTED=" + isConfigurationProtected);
      iframe.setHeight("100%");
      iframe.setWidth("100%");
      iframe.getElement().setAttribute("width", "100%");
      iframe.getElement().setAttribute("height", "100%");
      iframe.getElement().setAttribute("frameborder", "0");
      iframe.getElement().setPropertyString("scrolling", "no");
      iframe.getElement().getStyle().setProperty("border", "none");
      iframe.getElement().getStyle().setProperty("margin", "0");

      container.add(iframe, new VerticalLayoutData(1, 1, new Margins(0)));
      Scheduler.get().scheduleDeferred(new ScheduledCommand() {
         @Override
         public void execute() {
            wrapper.forceLayout();
            iframe.getElement().getStyle().setProperty("border", "none");
            iframe.getElement().getStyle().setProperty("margin", "0");

         }
      });

   }

   @Override
   protected void cancelExecution(String executeToken) {
      // TODO Auto-generated method stub
   }

   @Override
   public Request execute(final ReportDto report, String executeToken, final AsyncCallback<DwModel> callback) {
      return saikuDao.stashReport(getExecuteReportToken(), (SaikuReportDto) report, new RsAsyncCallback<Void>() {
         @Override
         public void onSuccess(Void result) {
            callback.onSuccess(report);
         }

         @Override
         public void onFailure(Throwable caught) {
            callback.onFailure(caught);
         }
      });
   }

   @Override
   public Widget doGetViewComponent() {
      return wrapper;
   }

}
