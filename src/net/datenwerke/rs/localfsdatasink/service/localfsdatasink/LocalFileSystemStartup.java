package net.datenwerke.rs.localfsdatasink.service.localfsdatasink;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.hooker.LocalFileSystemDatasinkProviderHooker;
import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.hooker.ScheduleAsLocalFileSystemEmailNotificationHooker;
import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.hooker.ScheduleConfigAsLocalFileSystemHooker;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;

public class LocalFileSystemStartup {

   @Inject
   public LocalFileSystemStartup(HookHandlerService hookHandler,
         Provider<LocalFileSystemDatasinkProviderHooker> localFileSystemDatasinkProvider,
         Provider<ScheduleAsLocalFileSystemEmailNotificationHooker> emailLocalFileSystemNotificationHooker,
         Provider<ScheduleConfigAsLocalFileSystemHooker> scheduleAsLocalFileSystemConfigHooker) {
      hookHandler.attachHooker(DatasinkProviderHook.class, localFileSystemDatasinkProvider);
      hookHandler.attachHooker(SchedulerExecutionHook.class, emailLocalFileSystemNotificationHooker);
      hookHandler.attachHooker(ScheduleConfigProviderHook.class, scheduleAsLocalFileSystemConfigHooker);
   }

}