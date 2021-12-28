package net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hookers;

import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.scheduler.client.scheduler.SchedulerDao;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.ScheduledReportListPanel;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto.ReportScheduleJobInformation;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto.ReportScheduleJobListInformation;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hooks.ScheduledReportListDetailToolbarHook;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class LoadDetailsForEntryHooker implements ScheduledReportListDetailToolbarHook {

   private final SchedulerDao schedulerDao;
   private final ToolbarService toolbarService;

   @Inject
   public LoadDetailsForEntryHooker(SchedulerDao schedulerDao, ToolbarService toolbarService) {

      /* store objects */
      this.schedulerDao = schedulerDao;
      this.toolbarService = toolbarService;
   }

   @Override
   public void statusBarToolbarHook_addLeft(ToolBar toolbar, final ReportScheduleJobListInformation info,
         final ReportScheduleJobInformation detailInfo, final ScheduledReportListPanel reportListPanel) {

      DwTextButton removeBtn = toolbarService.createSmallButtonLeft(SchedulerMessages.INSTANCE.loadDetailsLabel(),
            BaseIcon.LIST);
      removeBtn.addSelectHandler(new SelectHandler() {

         @Override
         public void onSelect(SelectEvent event) {
            reportListPanel.mask(BaseMessages.INSTANCE.loadingMsg());
            schedulerDao.loadDetailsFor(info, new RsAsyncCallback<ReportScheduleJobInformation>() {
               public void onSuccess(ReportScheduleJobInformation result) {
                  reportListPanel.setDataInDetailStore(result);
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
