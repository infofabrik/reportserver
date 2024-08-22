package net.datenwerke.rs.core.client.reportexecutor.ui.preview;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.http.client.Request;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.ResizeContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.model.DwModel;
import net.datenwerke.gxtdto.client.servercommunication.callback.ModalAsyncCallback;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorDao;
import net.datenwerke.rs.core.client.reportexecutor.events.ExecutorEventHandler;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportPreviewViewStatusbarHook;
import net.datenwerke.rs.core.client.reportexecutor.locale.ReportexecutorMessages;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanel;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanelView;
import net.datenwerke.rs.core.client.reportexecutor.ui.aware.EventHandlerAware;
import net.datenwerke.rs.core.client.reportexecutor.ui.aware.MainPanelAwareView;
import net.datenwerke.rs.core.client.reportexecutor.ui.aware.SelectionAwareView;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * This is the common baseclass for all ReportOutputUIs i.e. the preview Tabs
 * 
 *
 */
public abstract class AbstractReportPreviewView extends ReportExecutorMainPanelView
      implements SelectionAwareView, MainPanelAwareView, EventHandlerAware {

   public interface ExecutionDoneCallback {
      public void executionDone();
   }

   public static final String VIEW_ID = "preview";

   final protected ReportExecutorDao reportExecutorDao;
   final protected HookHandlerService hookHandler;

   protected ReportDto report;
   protected com.sencha.gxt.widget.core.client.Component mainPanel;

   protected VerticalLayoutContainer container;

   protected ExecutorEventHandler eventHandler;

   protected List<ReportPreviewViewStatusbarHook> statusbarHookers;

   protected boolean reportExecutionFailed = false;
   protected boolean duringReportExecution = false;

   protected int delayModalWindowOnExecution = 3000;

   protected List<ExecutionDoneCallback> executionCallbacks = new ArrayList<AbstractReportPreviewView.ExecutionDoneCallback>();

   @Inject
   public AbstractReportPreviewView(ReportExecutorDao rexService, HookHandlerService hookHandler) {

      super();

      /* store variables */
      this.reportExecutorDao = rexService;
      this.hookHandler = hookHandler;
   }

   @Override
   public String getViewId() {
      return VIEW_ID;
   }

   /**
    * Performs report type specific operations to display the report after its
    * execution.
    * 
    * @param reportExecutionResult
    */
   protected abstract void doLoadReport(DwModel reportExecutionResult);

   /**
    * Executes the report and displays the result
    */
   protected void executeAndDisplayReport(final boolean configChanged) {
      if (null != report) {
         setDuringReportExecution(true);
         setReportExecutionFailed(false);
         container.unmask();
         mainPanel.mask(BaseMessages.INSTANCE.loadingMsg());

         /* prepare execute token */
         String executeToken = null;
         if (mainPanel instanceof ReportExecutorMainPanel) {
            executeToken = ((ReportExecutorMainPanel) mainPanel).getExecuteReportToken();
         }
         final String execToken = executeToken + ":" + Double.toString(Math.random());

         AsyncCallback<DwModel> callback = new ModalAsyncCallback<DwModel>(delayModalWindowOnExecution,
               ReportexecutorMessages.INSTANCE.loadingDataTitle(), ReportexecutorMessages.INSTANCE.loadingDataMsg(),
               BaseMessages.INSTANCE.progressMsg()) {
            @Override
            public void doOnSuccess(DwModel result) {
               mainPanel.unmask();
               setDuringReportExecution(false);

               doLoadReport(result);

               if (isCreateStatusBar()) {
                  List<ReportPreviewViewStatusbarHook> statusbarHookers = getStatusBarHookers();
                  for (ReportPreviewViewStatusbarHook statusBarHooker : statusbarHookers)
                     statusBarHooker.reportPreviewViewStatusbarHook_reportLoaded(report, this, result, configChanged);
               }

               /* layout parent */
               if (null != mainPanel && mainPanel instanceof ResizeContainer)
                  ((ResizeContainer) mainPanel).forceLayout();
            }

            @Override
            public void doOnFailure(Throwable caught) {
               setReportExecutionFailed(true);
               setDuringReportExecution(false);
               mainPanel.unmask();
               container.mask(BaseMessages.INSTANCE.error() + ": " + caught.getMessage());
            }

            @Override
            protected void doOnCancel() {
               /* inform components */
               if (isCreateStatusBar()) {
                  List<ReportPreviewViewStatusbarHook> statusbarHookers = getStatusBarHookers();
                  for (ReportPreviewViewStatusbarHook statusBarHooker : statusbarHookers)
                     statusBarHooker.reportPreviewViewStatusbarHook_cancel(report, AbstractReportPreviewView.this);
               }

               cancelExecution(execToken);

               mainPanel.unmask();
               container.mask(BaseMessages.INSTANCE.requestCanceled());
            }
         };

         /* open modal window with timeout */
         Request request = execute(report, execToken, callback);
         ((ModalAsyncCallback<DwModel>) callback).setRequest(request);

         /* inform components */
         if (isCreateStatusBar()) {
            List<ReportPreviewViewStatusbarHook> statusbarHookers = getStatusBarHookers();
            for (ReportPreviewViewStatusbarHook statusBarHooker : statusbarHookers)
               statusBarHooker.reportPreviewViewStatusbarHook_reportUpdated(report, this, configChanged);
         }
      }
   }

   public abstract Request execute(ReportDto report, String executeToken, AsyncCallback<DwModel> callback);

   protected abstract void cancelExecution(String executeToken);

   @Override
   public String getComponentHeader() {
      return ReportexecutorMessages.INSTANCE.preview();
   }

   /**
    * Sets the report to be displayed
    * 
    * @param reportDto
    */
   public void setReport(ReportDto reportDto) {
      this.report = reportDto;
   }

   /**
    * 
    * @return the currently displayed report
    */
   public ReportDto getReport() {
      return report;
   }

   public void makeAwareOfSelection() {
      /* enable and show report */
      container.enable();
      executeAndDisplayReport(true);
   }
   
   public void makeAwareOfSelectionNoPreview() {
      container.enable();
      container.getElement().addClassName("mask-no-loading-bar");
      container.mask(ReportexecutorMessages.INSTANCE.previewDisabledLabel());
   }

   public void reload() {
      if (isCreateStatusBar()) {
         List<ReportPreviewViewStatusbarHook> statusbarHookers = getStatusBarHookers();
         for (ReportPreviewViewStatusbarHook statusBarHooker : statusbarHookers)
            statusBarHooker.reportPreviewViewStatusbarHook_reportToBeReloaded(report, this);
      }
      executeAndDisplayReport(true);
   }

   protected List<ReportPreviewViewStatusbarHook> getStatusBarHookers() {
      if (null == statusbarHookers)
         statusbarHookers = hookHandler.getHookers(ReportPreviewViewStatusbarHook.class);

      return statusbarHookers;
   }

   @Override
   public void makeAwareOfMainPanel(com.sencha.gxt.widget.core.client.Component mainPanel) {
      this.mainPanel = mainPanel;
   }

   @Override
   public Component getViewComponent() {
      container = new VerticalLayoutContainer();

      /* get actual component and add it to container */
      Widget widget = doGetViewComponent();
      container.add(widget, new VerticalLayoutData(1, 1));

      /* create statusbar */
      if (isCreateStatusBar()) {
         DwToolBar statusBar = new DwToolBar();
         statusBar.addClassName("rs-report-preview-sb");
         statusBar.setHeight(38);
         statusBar.setEnableOverflow(false);
         fillStatus(statusBar);
         container.add(statusBar, new VerticalLayoutData(1, -1));
      }

      return container;
   }

   protected boolean isCreateStatusBar() {
      return true;
   }

   protected void fillStatus(ToolBar statusBar) {
      if (!isCreateStatusBar())
         return;

      List<ReportPreviewViewStatusbarHook> hookers = getStatusBarHookers();

      for (ReportPreviewViewStatusbarHook hooker : hookers)
         hooker.reportPreviewViewStatusbarHook_addLeft(this, statusBar, getReport());

      statusBar.add(new FillToolItem());

      for (ReportPreviewViewStatusbarHook hooker : hookers)
         hooker.reportPreviewViewStatusbarHook_addRight(this, statusBar, getReport());

   }

   public abstract Widget doGetViewComponent();

   @Override
   public ImageResource getIcon() {
      return BaseIcon.EYE.toImageResource();
   }

   @Override
   public void makeAwareOfEventHandler(ExecutorEventHandler eventHandler) {
      this.eventHandler = eventHandler;
   }

   public boolean isReportExecutionFailed() {
      return reportExecutionFailed;
   }

   public void setReportExecutionFailed(boolean reportExecutionFailed) {
      this.reportExecutionFailed = reportExecutionFailed;
   }

   public boolean isDuringReportExecution() {
      return duringReportExecution;
   }

   public void setDuringReportExecution(boolean duringReportExecution) {
      this.duringReportExecution = duringReportExecution;

      if (!duringReportExecution) {
         for (ExecutionDoneCallback cb : executionCallbacks)
            cb.executionDone();
         executionCallbacks.clear();
      }
   }

   public void callbackOnExecutionDone(ExecutionDoneCallback callback) {
      if (!isDuringReportExecution())
         callback.executionDone();
      else
         executionCallbacks.add(callback);
   }

   public void setDelayModalWindowOnExecution(int delayModalWindowOnExecution) {
      this.delayModalWindowOnExecution = delayModalWindowOnExecution;
   }

   public int getDelayModalWindowOnExecution() {
      return delayModalWindowOnExecution;
   }

}
