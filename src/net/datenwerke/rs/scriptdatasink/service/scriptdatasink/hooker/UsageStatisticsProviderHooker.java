package net.datenwerke.rs.scriptdatasink.service.scriptdatasink.hooker;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.core.service.datasinkmanager.hooks.UsageStatisticsDatasinkEntryProviderHook;
import net.datenwerke.rs.scriptdatasink.service.scriptdatasink.definitions.ScriptDatasink;
import net.datenwerke.rs.scriptdatasink.service.scriptdatasink.locale.ScriptDatasinkMessages;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class UsageStatisticsProviderHooker implements UsageStatisticsDatasinkEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String TYPE = "SCRIPT_DATASINKS";
   
   @Inject
   public UsageStatisticsProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      return usageStatisticsService.provideNodeCountValueEntry(TYPE,
            ScriptDatasinkMessages.INSTANCE.scriptDatasinks(), ScriptDatasink.class);
   }

}
