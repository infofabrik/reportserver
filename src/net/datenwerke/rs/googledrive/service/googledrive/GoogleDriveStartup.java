package net.datenwerke.rs.googledrive.service.googledrive;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasinkmanager.hookers.factory.DatasinkDefaultMergeHookerFactory;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.UsageStatisticsDatasinkEntryProviderHook;
import net.datenwerke.rs.googledrive.service.googledrive.definitions.GoogleDriveDatasink;
import net.datenwerke.rs.googledrive.service.googledrive.hooker.GoogleDriveDatasinkProviderHooker;
import net.datenwerke.rs.googledrive.service.googledrive.hooker.ScheduleAsGoogleDriveFileEmailNotificationHooker;
import net.datenwerke.rs.googledrive.service.googledrive.hooker.ScheduleConfigAsGoogleDriveDatasinkHooker;
import net.datenwerke.rs.googledrive.service.googledrive.hooker.UsageStatisticsProviderHooker;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;

public class GoogleDriveStartup {

   @Inject
   public GoogleDriveStartup(
         final HookHandlerService hookHandler,
         final Provider<GoogleDriveDatasinkProviderHooker> googleDriveDatasinkProviderHooker,
         final Provider<ScheduleAsGoogleDriveFileEmailNotificationHooker> emailGoogleDriveNotificationHooker,
         final Provider<ScheduleConfigAsGoogleDriveDatasinkHooker> scheduleAsGoogleDriveConfigHooker,
         final Provider<UsageStatisticsProviderHooker> usageStats,
         
         final Provider<DatasinkDefaultMergeHookerFactory> datasinkFactory
         ) {
      hookHandler.attachHooker(DatasinkProviderHook.class, googleDriveDatasinkProviderHooker);
      hookHandler.attachHooker(SchedulerExecutionHook.class, emailGoogleDriveNotificationHooker);
      hookHandler.attachHooker(ScheduleConfigProviderHook.class, scheduleAsGoogleDriveConfigHooker);
      
      /* entity merge */
      hookHandler.attachHooker(EntityMergeHook.class, datasinkFactory.get().create(GoogleDriveDatasink.class));
      
      hookHandler.attachHooker(UsageStatisticsDatasinkEntryProviderHook.class, usageStats,
            HookHandlerService.PRIORITY_MEDIUM + 30);
   }

}
