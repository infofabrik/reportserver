package net.datenwerke.rs.amazons3.service.amazons3;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;
import net.datenwerke.rs.amazons3.service.amazons3.hooker.AmazonS3DatasinkProviderHooker;
import net.datenwerke.rs.amazons3.service.amazons3.hooker.ScheduleAsAmazonS3FileEmailNotificationHooker;
import net.datenwerke.rs.amazons3.service.amazons3.hooker.ScheduleConfigAsAmazonS3DatasinkHooker;

public class AmazonS3Startup {

   @Inject
   public AmazonS3Startup(HookHandlerService hookHandler,
         Provider<AmazonS3DatasinkProviderHooker> amazonS3DatasinkProviderHooker,
         Provider<ScheduleAsAmazonS3FileEmailNotificationHooker> emailAmazonS3NotificationHooker,
         Provider<ScheduleConfigAsAmazonS3DatasinkHooker> scheduleAsAmazonS3ConfigHooker) {
      hookHandler.attachHooker(DatasinkProviderHook.class, amazonS3DatasinkProviderHooker);
      hookHandler.attachHooker(SchedulerExecutionHook.class, emailAmazonS3NotificationHooker);
      hookHandler.attachHooker(ScheduleConfigProviderHook.class, scheduleAsAmazonS3ConfigHooker);
   }

}
