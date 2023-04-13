package net.datenwerke.rs.tabledatasink.service.tabledatasink.hooker;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.core.service.datasinkmanager.hooks.UsageStatisticsDatasinkEntryProviderHook;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.definitions.TableDatasink;
import net.datenwerke.rs.tabledatasink.service.tabledatasink.locale.TableDatasinkMessages;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class UsageStatisticsProviderHooker implements UsageStatisticsDatasinkEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String TYPE = "TABLE_DATASINKS";
   
   @Inject
   public UsageStatisticsProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      return usageStatisticsService.provideNodeCountValueEntry(TYPE,
            TableDatasinkMessages.INSTANCE.tableDatasinks(), TableDatasink.class);
   }

}
