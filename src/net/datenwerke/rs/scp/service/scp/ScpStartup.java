package net.datenwerke.rs.scp.service.scp;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.scp.service.scp.hooker.ScheduleAsScpFileEmailNotificationHooker;
import net.datenwerke.rs.scp.service.scp.hooker.ScheduleConfigAsScpFileHooker;
import net.datenwerke.rs.scp.service.scp.hooker.ScpDatasinkProviderHooker;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;

public class ScpStartup {
   @Inject
   public ScpStartup(
         HookHandlerService hookHandler, 
         Provider<ScpDatasinkProviderHooker> scpDatasinkProvider,
         Provider<ScheduleConfigAsScpFileHooker> scheduleAsScpFileConfigHooker,
         Provider<ScheduleAsScpFileEmailNotificationHooker> emailScpNotificationHooker

   ) {

      hookHandler.attachHooker(DatasinkProviderHook.class, scpDatasinkProvider);
      hookHandler.attachHooker(ScheduleConfigProviderHook.class, scheduleAsScpFileConfigHooker);
      hookHandler.attachHooker(SchedulerExecutionHook.class, emailScpNotificationHooker);

   }

}
