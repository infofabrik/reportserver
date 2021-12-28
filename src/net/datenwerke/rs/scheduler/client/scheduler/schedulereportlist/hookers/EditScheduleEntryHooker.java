package net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hookers;

import java.util.ArrayList;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.wizard.WizardDialog;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.sendto.SendToClientConfig;
import net.datenwerke.rs.core.client.sendto.SendToDao;
import net.datenwerke.rs.scheduler.client.scheduler.SchedulerDao;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.ScheduleDialog;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereport.ScheduleDialog.DialogCallback;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.ScheduledReportListPanel;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.SchedulerAdminModule;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto.ReportScheduleJobInformation;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto.ReportScheduleJobListInformation;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hooks.ScheduledReportListDetailToolbarHook;
import net.datenwerke.rs.scheduler.client.scheduler.security.SchedulingAdminViewGenericTargetIdentifier;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ExecuteDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;

public class EditScheduleEntryHooker implements ScheduledReportListDetailToolbarHook {

   private final LoginService loginService;
   private final SchedulerDao schedulerDao;
   private final ToolbarService toolbarService;
   private final Provider<ScheduleDialog> scheduleDialogProvider;
   private final SendToDao sendToDao;

   private final SecurityUIService securityService;

   @Inject
   public EditScheduleEntryHooker(LoginService loginService, SchedulerDao schedulerDao, ToolbarService toolbarService,
         Provider<ScheduleDialog> multiDialogProvider, SendToDao sendToDao, final SecurityUIService securityService) {

      /* store objects */
      this.loginService = loginService;
      this.schedulerDao = schedulerDao;
      this.toolbarService = toolbarService;
      this.scheduleDialogProvider = multiDialogProvider;
      this.sendToDao = sendToDao;
      this.securityService = securityService;
   }

   @Override
   public void statusBarToolbarHook_addLeft(ToolBar toolbar, final ReportScheduleJobListInformation info,
         final ReportScheduleJobInformation detailInfo, final ScheduledReportListPanel reportListPanel) {

      /* only for selected user */
      UserDto user = loginService.getCurrentUser();
      if (!detailInfo.isOwner(user) && !user.isSuperUser()) {
         /* If we are in the admin-panel: we check for scheduling-admin rights. */
         if (reportListPanel.getName().equals(SchedulerAdminModule.ADMIN_FILTER_PANEL)) {
            if (!securityService.hasRight(SchedulingAdminViewGenericTargetIdentifier.class, ExecuteDto.class)) {
               return;
            }
         } else {
            /* We are not in the admin panel. */
            return;
         }
      }

      DwTextButton editBtn = toolbarService.createSmallButtonLeft(SchedulerMessages.INSTANCE.editScheduledJobLabel(),
            BaseIcon.CLOCK_EDIT);
      editBtn.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            reportListPanel.mask(BaseMessages.INSTANCE.loadingMsg());
            schedulerDao.getReportFor(info.getJobId(), new RsAsyncCallback<ReportDto>() {
               public void onSuccess(final ReportDto report) {
                  sendToDao.loadClientConfigsFor(report, new RsAsyncCallback<ArrayList<SendToClientConfig>>() {
                     @Override
                     public void onSuccess(ArrayList<SendToClientConfig> sendToConfigs) {
                        reportListPanel.unmask();

                        ScheduleDialog dialog = scheduleDialogProvider.get();

                        ReportScheduleDefinition definition = detailInfo.getScheduleDefinition();
                        dialog.displayEdit(definition, sendToConfigs, report, new DialogCallback() {

                           @Override
                           public void finished(ReportScheduleDefinition configDto, final WizardDialog dialog) {
                              dialog.mask(BaseMessages.INSTANCE.storingMsg());

                              schedulerDao.reschedule(info.getJobId(), configDto,
                                    new NotamCallback<Void>(SchedulerMessages.INSTANCE.scheduled()) {
                                       @Override
                                       public void doOnSuccess(Void result) {
                                          dialog.hide();
                                          reportListPanel.reload();
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
                     public void onFailure(Throwable caught) {
                        super.onFailure(caught);
                        reportListPanel.unmask();
                     }
                  });
               };

               @Override
               public void onFailure(Throwable caught) {
                  reportListPanel.unmask();
               }
            });
         }
      });

      toolbar.add(editBtn);
   }

   @Override
   public void statusBarToolbarHook_addRight(ToolBar toolbar, ReportScheduleJobListInformation info,
         ReportScheduleJobInformation detailInfo, ScheduledReportListPanel reportListPanel) {

   }

}
