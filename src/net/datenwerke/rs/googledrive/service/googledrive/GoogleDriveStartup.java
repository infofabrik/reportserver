package net.datenwerke.rs.googledrive.service.googledrive;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.googledrive.service.googledrive.hooker.GoogleDriveDatasinkProviderHooker;
import net.datenwerke.rs.googledrive.service.googledrive.hooker.ScheduleAsGoogleDriveFileEmailNotificationHooker;
import net.datenwerke.rs.googledrive.service.googledrive.hooker.ScheduleConfigAsGoogleDriveDatasinkHooker;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;

public class GoogleDriveStartup {

   @Inject
   public GoogleDriveStartup(HookHandlerService hookHandler,
         Provider<GoogleDriveDatasinkProviderHooker> googleDriveDatasinkProviderHooker,
         Provider<ScheduleAsGoogleDriveFileEmailNotificationHooker> emailGoogleDriveNotificationHooker,
         Provider<ScheduleConfigAsGoogleDriveDatasinkHooker> scheduleAsGoogleDriveConfigHooker) {
      hookHandler.attachHooker(DatasinkProviderHook.class, googleDriveDatasinkProviderHooker);
      hookHandler.attachHooker(SchedulerExecutionHook.class, emailGoogleDriveNotificationHooker);
      hookHandler.attachHooker(ScheduleConfigProviderHook.class, scheduleAsGoogleDriveConfigHooker);
   }

}
