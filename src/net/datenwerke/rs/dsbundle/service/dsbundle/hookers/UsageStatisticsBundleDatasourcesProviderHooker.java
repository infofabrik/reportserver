package net.datenwerke.rs.dsbundle.service.dsbundle.hookers;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.core.service.datasourcemanager.hooks.UsageStatisticsDatasourceEntryProviderHook;
import net.datenwerke.rs.dsbundle.service.dsbundle.entities.DatabaseBundle;
import net.datenwerke.rs.dsbundle.service.dsbundle.locale.DatasourceBundleMessages;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class UsageStatisticsBundleDatasourcesProviderHooker implements UsageStatisticsDatasourceEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String TYPE = "DATABASE_BUNDLES";
   
   @Inject
   public UsageStatisticsBundleDatasourcesProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      return usageStatisticsService.provideNodeCountValueEntry(TYPE, DatasourceBundleMessages.INSTANCE.databaseBundles(),
            DatabaseBundle.class);
   }

}
