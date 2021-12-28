package net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hookers;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.scheduler.client.scheduler.SchedulerDao;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.ScheduledReportListPanel;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto.ReportScheduleJobInformation;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto.ReportScheduleJobListInformation;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hooks.ScheduledReportListDetailToolbarHook;
import net.datenwerke.rs.scheduler.client.scheduler.security.SchedulingAdminViewGenericTargetIdentifier;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ExecuteDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;

public class ScheduleNowHooker implements ScheduledReportListDetailToolbarHook {

   private final LoginService loginService;
   private final SchedulerDao schedulerDao;
   private final ToolbarService toolbarService;
   private final SecurityUIService securityService;

   @Inject
   public ScheduleNowHooker(LoginService loginService, SchedulerDao schedulerDao, SecurityUIService securityService,
         ToolbarService toolbarService) {

      this.loginService = loginService;
      /* store objects */
      this.schedulerDao = schedulerDao;
      this.securityService = securityService;
      this.toolbarService = toolbarService;
   }

   @Override
   public void statusBarToolbarHook_addLeft(ToolBar toolbar, final ReportScheduleJobListInformation info,
         final ReportScheduleJobInformation detailInfo, final ScheduledReportListPanel reportListPanel) {

      /* only for selected user */
      UserDto user = loginService.getCurrentUser();
      if (!detailInfo.isOwner(user) && !user.isSuperUser()
            && !securityService.hasRight(SchedulingAdminViewGenericTargetIdentifier.class, ExecuteDto.class))
         return;

      DwTextButton removeBtn = toolbarService.createSmallButtonLeft(SchedulerMessages.INSTANCE.scheduleNowLabel(),
            BaseIcon.PLAY);
      removeBtn.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            reportListPanel.mask(BaseMessages.INSTANCE.loadingMsg());
            schedulerDao.scheduleOnce(info.getJobId(), new AsyncCallback<Void>() {

               @Override
               public void onSuccess(Void result) {
                  reportListPanel.unmask();
               }

               @Override
               public void onFailure(Throwable caught) {
                  reportListPanel.unmask();
               }
            });
         }
      });
      toolbar.add(removeBtn);

   }

   @Override
   public void statusBarToolbarHook_addRight(ToolBar toolbar, ReportScheduleJobListInformation info,
         ReportScheduleJobInformation detailInfo, ScheduledReportListPanel reportListPanel) {

   }

}
