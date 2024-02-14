package net.datenwerke.rs.scriptdatasink.service.scriptdatasink;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasinkmanager.hookers.factory.DatasinkDefaultMergeHookerFactory;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.UsageStatisticsDatasinkEntryProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.scriptdatasink.service.scriptdatasink.definitions.ScriptDatasink;
import net.datenwerke.rs.scriptdatasink.service.scriptdatasink.hooker.ScheduleAsScriptDatasinkEmailNotificationHooker;
import net.datenwerke.rs.scriptdatasink.service.scriptdatasink.hooker.ScheduleConfigAsScriptDatasinkHooker;
import net.datenwerke.rs.scriptdatasink.service.scriptdatasink.hooker.ScriptDatasinkProviderHooker;
import net.datenwerke.rs.scriptdatasink.service.scriptdatasink.hooker.UsageStatisticsProviderHooker;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;

public class ScriptDatasinkStartup {

   @Inject
   public ScriptDatasinkStartup(
         final HookHandlerService hookHandler,
         final Provider<ScriptDatasinkProviderHooker> scriptDatasinkProvider,
         final Provider<ScheduleAsScriptDatasinkEmailNotificationHooker> emailScriptDatasinkNotificationHooker,
         final Provider<ScheduleConfigAsScriptDatasinkHooker> scheduleAsScriptDatasinkConfigHooker,
         
         final Provider<UsageStatisticsProviderHooker> usageStats,
         final Provider<DatasinkDefaultMergeHookerFactory> datasinkFactory
         ) {
      hookHandler.attachHooker(DatasinkProviderHook.class, scriptDatasinkProvider);
      hookHandler.attachHooker(SchedulerExecutionHook.class, emailScriptDatasinkNotificationHooker);
      hookHandler.attachHooker(ScheduleConfigProviderHook.class, scheduleAsScriptDatasinkConfigHooker);
      
      /* entity merge */
      hookHandler.attachHooker(EntityMergeHook.class, datasinkFactory.get().create(ScriptDatasink.class));
      
      hookHandler.attachHooker(UsageStatisticsDatasinkEntryProviderHook.class, usageStats,
            HookHandlerService.PRIORITY_MEDIUM + 60);
   }

}