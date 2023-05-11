package net.datenwerke.rs.scriptdatasource.service.scriptdatasource;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasourcemanager.hooks.UsageStatisticsDatasourceEntryProviderHook;
import net.datenwerke.rs.scriptdatasource.service.scriptdatasource.hookers.UsageStatisticsScriptDatasourcesProviderHooker;

public class ScriptDatasourceStartup {

   @Inject
   public ScriptDatasourceStartup(
         final HookHandlerService hookHandler,
         final Provider<UsageStatisticsScriptDatasourcesProviderHooker> usageStatsScriptDatasourceProvider
         ) {
      hookHandler.attachHooker(UsageStatisticsDatasourceEntryProviderHook.class, usageStatsScriptDatasourceProvider,
            HookHandlerService.PRIORITY_LOW + 25);
   }
}

