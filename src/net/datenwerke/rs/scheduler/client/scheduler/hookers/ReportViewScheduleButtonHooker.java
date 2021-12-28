package net.datenwerke.rs.scheduler.client.scheduler.hookers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.wizard.WizardDialog;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportExecutorViewToolbarHook;
import net.datenwerke.rs.core.client.reportexecutor.reportdispatcher.InlineReportView;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorInformation;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanel;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanelView;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterUIService;
import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporter;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.decorator.ReportDtoDec;
import net.datenwerke.rs.core.client.sendto.SendToClientConfig;
import net.datenwerke.rs.core.client.sendto.SendToDao;
import net.datenwerke.rs.scheduler.client.scheduler.SchedulerDao;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.ScheduleDialog;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.ScheduleDialog.DialogCallback;
import net.datenwerke.rs.scheduler.client.scheduler.security.SchedulingBasicGenericTargetIdentifier;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ExecuteDto;

public class ReportViewScheduleButtonHooker implements ReportExecutorViewToolbarHook {

   private final Provider<ScheduleDialog> multiDialogProvider;
   private final SchedulerDao schedulerDao;
   private final SecurityUIService securityService;
   private final SendToDao sendToDao;

   private final ReportExporterUIService reportExporterService;
   private ScheduleDialog multiDialog;

   @Inject
   public ReportViewScheduleButtonHooker(Provider<ScheduleDialog> multiDialogProvider, SchedulerDao schedulerDao,
         SecurityUIService securityService, ReportExporterUIService reportExporterService, SendToDao sendToDao) {

      /* store objects */
      this.multiDialogProvider = multiDialogProvider;
      this.schedulerDao = schedulerDao;
      this.securityService = securityService;
      this.reportExporterService = reportExporterService;
      this.sendToDao = sendToDao;
   }

   @Override
   public boolean reportPreviewViewToolbarHook_addLeft(ToolBar toolbar, final ReportDto report,
         ReportExecutorInformation info, final ReportExecutorMainPanel mainPanel) {
      if (!securityService.hasRight(SchedulingBasicGenericTargetIdentifier.class, ExecuteDto.class))
         return false;

      /* only if report can be exported */
      List<ReportExporter> exporters = reportExporterService.getCleanedUpAvailableExporters(report);
      if (!exporters.iterator().hasNext())
         return false;

      for (ReportViewConfiguration config : mainPanel.getViewConfigs())
         if (config instanceof InlineReportView)
            return false;

      /* repeated dialog */
      DwTextButton schedulerDialogBtn = new DwTextButton(SchedulerMessages.INSTANCE.repeatedly(), BaseIcon.CALENDAR);
      schedulerDialogBtn.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            if (!report.isModified()) {
               ArrayList<String> errorMsgs = new ArrayList<String>();
               for (ReportExecutorMainPanelView view : mainPanel.getViews()) {
                  errorMsgs.addAll(view.validateView());
               }
               if (null != errorMsgs && !errorMsgs.isEmpty()) {
                  String errorMsg = "";
                  boolean first = true;
                  for (String msg : errorMsgs) {
                     if (first)
                        first = false;
                     else
                        errorMsg += "<br/>";
                     errorMsg += msg;
                  }

                  new DwAlertMessageBox(BaseMessages.INSTANCE.error(), errorMsg).show();
               } else {
                  displayDialog(report, mainPanel, mainPanel.getViewConfigs());
               }
            } else {

               ConfirmMessageBox cmb = new DwConfirmMessageBox(SchedulerMessages.INSTANCE.reportChangedInfoHeader(),
                     SchedulerMessages.INSTANCE.reportChangedInfoMessage());
               cmb.addDialogHideHandler(new DialogHideHandler() {
                  @Override
                  public void onDialogHide(DialogHideEvent event) {
                     if (event.getHideButton() == PredefinedButton.YES)
                        displayDialog(report, mainPanel, mainPanel.getViewConfigs());

                  }
               });

               cmb.show();
            }

         }
      });
      toolbar.add(schedulerDialogBtn);

      if (!(report instanceof ReportVariantDto) && !((ReportDtoDec) report).isBaseReportExecutable())
         schedulerDialogBtn.disable();

      return true;
   }

   protected void displayDialog(final ReportDto report, final ReportExecutorMainPanel mainPanel,
         final Collection<ReportViewConfiguration> viewConfigs) {

      mainPanel.mask(BaseMessages.INSTANCE.loadingMsg());
      sendToDao.loadClientConfigsFor(report, new RsAsyncCallback<ArrayList<SendToClientConfig>>() {
         @Override
         public void onSuccess(ArrayList<SendToClientConfig> result) {
            mainPanel.unmask();
            doDisplayDialog(report, viewConfigs, result);
         }

         @Override
         public void onFailure(Throwable caught) {
            mainPanel.unmask();
         }
      });

   }

   protected void doDisplayDialog(ReportDto report, Collection<ReportViewConfiguration> viewConfigs,
         ArrayList<SendToClientConfig> sendToConfigs) {
      if (null == multiDialog)
         multiDialog = multiDialogProvider.get();

      multiDialog.displayDialog(Optional.ofNullable(report), viewConfigs, sendToConfigs, new DialogCallback() {
         @Override
         public void finished(ReportScheduleDefinition configDto, final WizardDialog dialog) {
            dialog.mask(BaseMessages.INSTANCE.storingMsg());
            schedulerDao.schedule(configDto, new NotamCallback<Void>(SchedulerMessages.INSTANCE.scheduled()) {
               @Override
               public void doOnSuccess(Void result) {
                  dialog.hide();
               }

               @Override
               public void doOnFailure(Throwable caught) {
                  super.doOnFailure(caught);
                  dialog.unmask();
               }
            });
         }
      });
   }

   @Override
   public boolean reportPreviewViewToolbarHook_addRight(ToolBar toolbar, ReportDto report,
         ReportExecutorInformation info, ReportExecutorMainPanel mainPanel) {
      return false;
   }

   @Override
   public void reportPreviewViewToolbarHook_reportUpdated(ReportDto report, ReportExecutorInformation info) {

   }

}
