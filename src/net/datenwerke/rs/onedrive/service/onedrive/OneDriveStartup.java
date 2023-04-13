package net.datenwerke.rs.onedrive.service.onedrive;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.UsageStatisticsDatasinkEntryProviderHook;
import net.datenwerke.rs.onedrive.service.onedrive.hooker.OneDriveDatasinkProviderHooker;
import net.datenwerke.rs.onedrive.service.onedrive.hooker.ScheduleAsOneDriveFileEmailNotificationHooker;
import net.datenwerke.rs.onedrive.service.onedrive.hooker.ScheduleConfigAsOneDriveDatasinkHooker;
import net.datenwerke.rs.onedrive.service.onedrive.hooker.UsageStatisticsProviderHooker;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;

public class OneDriveStartup {

   @Inject
   public OneDriveStartup(
         final HookHandlerService hookHandler,
         final Provider<OneDriveDatasinkProviderHooker> oneDriveDatasinkProviderHooker,
         final Provider<ScheduleAsOneDriveFileEmailNotificationHooker> emailOneDriveNotificationHooker,
         final Provider<ScheduleConfigAsOneDriveDatasinkHooker> scheduleAsOneDriveConfigHooker,
         final Provider<UsageStatisticsProviderHooker> usageStats
         ) {
      hookHandler.attachHooker(DatasinkProviderHook.class, oneDriveDatasinkProviderHooker);
      hookHandler.attachHooker(SchedulerExecutionHook.class, emailOneDriveNotificationHooker);
      hookHandler.attachHooker(ScheduleConfigProviderHook.class, scheduleAsOneDriveConfigHooker);
      
      hookHandler.attachHooker(UsageStatisticsDatasinkEntryProviderHook.class, usageStats,
            HookHandlerService.PRIORITY_MEDIUM + 40);
   }
}