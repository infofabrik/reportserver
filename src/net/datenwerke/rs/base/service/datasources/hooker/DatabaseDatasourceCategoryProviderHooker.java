package net.datenwerke.rs.base.service.datasources.hooker;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.inject.Provider;

import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.base.service.datasources.hooks.UsageStatisticsDatabaseDatasourceEntryProviderHook;
import net.datenwerke.rs.base.service.datasources.locale.DatasourcesMessages;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class DatabaseDatasourceCategoryProviderHooker implements GeneralInfoCategoryProviderHook {

   private final Provider<UsageStatisticsService> usageStatisticsServiceProvider;
   
   private final static String TYPE = "DATABASE_DATASOURCE_STATISTICS";
   
   @Inject
   public DatabaseDatasourceCategoryProviderHooker(
         Provider<UsageStatisticsService> usageStatisticsServiceProvider
         ) {
      this.usageStatisticsServiceProvider = usageStatisticsServiceProvider;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Map<ImmutablePair<String, String>, Object>> provideCategory() {
      return usageStatisticsServiceProvider.get().provideCategory(TYPE,
            DatasourcesMessages.INSTANCE.relationalDatasourcesStatistics(), UsageStatisticsDatabaseDatasourceEntryProviderHook.class);
   }

}
