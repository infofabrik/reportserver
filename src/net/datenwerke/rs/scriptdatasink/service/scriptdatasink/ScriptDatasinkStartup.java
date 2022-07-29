package net.datenwerke.rs.scriptdatasink.service.scriptdatasink;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.scriptdatasink.service.scriptdatasink.hooker.ScheduleAsScriptDatasinkEmailNotificationHooker;
import net.datenwerke.rs.scriptdatasink.service.scriptdatasink.hooker.ScheduleConfigAsScriptDatasinkHooker;
import net.datenwerke.rs.scriptdatasink.service.scriptdatasink.hooker.ScriptDatasinkProviderHooker;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;

public class ScriptDatasinkStartup {

   @Inject
   public ScriptDatasinkStartup(
         HookHandlerService hookHandler,
         Provider<ScriptDatasinkProviderHooker> scriptDatasinkProvider,
         Provider<ScheduleAsScriptDatasinkEmailNotificationHooker> emailScriptDatasinkNotificationHooker,
         Provider<ScheduleConfigAsScriptDatasinkHooker> scheduleAsScriptDatasinkConfigHooker
         ) {
      hookHandler.attachHooker(DatasinkProviderHook.class, scriptDatasinkProvider);
      hookHandler.attachHooker(SchedulerExecutionHook.class, emailScriptDatasinkNotificationHooker);
      hookHandler.attachHooker(ScheduleConfigProviderHook.class, scheduleAsScriptDatasinkConfigHooker);
   }

}