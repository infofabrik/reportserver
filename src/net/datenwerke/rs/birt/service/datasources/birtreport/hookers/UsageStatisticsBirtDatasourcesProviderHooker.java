package net.datenwerke.rs.birt.service.datasources.birtreport.hookers;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceDefinition;
import net.datenwerke.rs.birt.service.datasources.locale.BirtMessages;
import net.datenwerke.rs.core.service.datasourcemanager.hooks.UsageStatisticsDatasourceEntryProviderHook;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class UsageStatisticsBirtDatasourcesProviderHooker implements UsageStatisticsDatasourceEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String TYPE = "BIRT_DATASOURCES";
   
   @Inject
   public UsageStatisticsBirtDatasourcesProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      return usageStatisticsService.provideNodeCountValueEntry(TYPE, BirtMessages.INSTANCE.birtReportDatasources(),
            BirtReportDatasourceDefinition.class);
   }

}
