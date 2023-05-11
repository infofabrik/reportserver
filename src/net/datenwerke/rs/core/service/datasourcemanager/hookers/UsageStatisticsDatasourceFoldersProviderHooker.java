package net.datenwerke.rs.core.service.datasourcemanager.hookers;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceFolder;
import net.datenwerke.rs.core.service.datasourcemanager.hooks.UsageStatisticsDatasourceEntryProviderHook;
import net.datenwerke.rs.core.service.datasourcemanager.locale.DatasourceManagerMessages;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class UsageStatisticsDatasourceFoldersProviderHooker implements UsageStatisticsDatasourceEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String TYPE = "TOTAL_DATASOURCE_FOLDERS";
   
   @Inject
   public UsageStatisticsDatasourceFoldersProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      return usageStatisticsService.provideNodeCountValueEntry(TYPE,
            DatasourceManagerMessages.INSTANCE.folders(), DatasourceFolder.class);
   }

}
