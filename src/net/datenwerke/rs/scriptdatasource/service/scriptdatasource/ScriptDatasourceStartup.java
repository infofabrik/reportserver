package net.datenwerke.rs.scriptdatasource.service.scriptdatasource;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasourcemanager.hookers.factory.DatasourceDefaultMergeHookerFactory;
import net.datenwerke.rs.core.service.datasourcemanager.hooks.UsageStatisticsDatasourceEntryProviderHook;
import net.datenwerke.rs.scriptdatasource.service.scriptdatasource.entities.ScriptDatasource;
import net.datenwerke.rs.scriptdatasource.service.scriptdatasource.hookers.UsageStatisticsScriptDatasourcesProviderHooker;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;

public class ScriptDatasourceStartup {

   @Inject
   public ScriptDatasourceStartup(
         final HookHandlerService hookHandler,
         final Provider<UsageStatisticsScriptDatasourcesProviderHooker> usageStatsScriptDatasourceProvider,
         final Provider<DatasourceDefaultMergeHookerFactory> datasourceFactory
         ) {
      hookHandler.attachHooker(UsageStatisticsDatasourceEntryProviderHook.class, usageStatsScriptDatasourceProvider,
            HookHandlerService.PRIORITY_LOW + 25);
      
      /* entity merge */
      hookHandler.attachHooker(EntityMergeHook.class, datasourceFactory.get().create(ScriptDatasource.class));
   }
}

