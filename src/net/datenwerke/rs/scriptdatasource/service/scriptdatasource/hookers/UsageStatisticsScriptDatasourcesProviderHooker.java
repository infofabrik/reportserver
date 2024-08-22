package net.datenwerke.rs.scriptdatasource.service.scriptdatasource.hookers;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.core.service.datasourcemanager.hooks.UsageStatisticsDatasourceEntryProviderHook;
import net.datenwerke.rs.scriptdatasource.service.scriptdatasource.entities.ScriptDatasource;
import net.datenwerke.rs.scriptdatasource.service.scriptdatasource.locale.ScriptDatasourceMessages;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class UsageStatisticsScriptDatasourcesProviderHooker implements UsageStatisticsDatasourceEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String TYPE = "SCRIPT_DATASOURCES";
   
   @Inject
   public UsageStatisticsScriptDatasourcesProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      return usageStatisticsService.provideNodeCountValueEntry(TYPE, ScriptDatasourceMessages.INSTANCE.scriptDatasources(),
            ScriptDatasource.class);
   }

}
