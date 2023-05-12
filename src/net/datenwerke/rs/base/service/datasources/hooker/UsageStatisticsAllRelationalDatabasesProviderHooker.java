package net.datenwerke.rs.base.service.datasources.hooker;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toMap;

import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.base.service.datasources.hooks.UsageStatisticsDatabaseDatasourceEntryProviderHook;
import net.datenwerke.rs.base.service.dbhelper.DBHelperService;
import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class UsageStatisticsAllRelationalDatabasesProviderHooker implements UsageStatisticsDatabaseDatasourceEntryProviderHook {

   private final DBHelperService dbHelperService;
   private final UsageStatisticsService usageStatisticsService;
   
   @Inject
   public UsageStatisticsAllRelationalDatabasesProviderHooker(
         DBHelperService dbHelperService,
         UsageStatisticsService usageStatisticsService
         ) {
      this.dbHelperService = dbHelperService;
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      return dbHelperService.getDatabaseHelpers()
         .stream()
         .sorted(comparing(DatabaseHelper::getName))
         .map(db -> new SimpleEntry<>(ImmutablePair.of(db.getDescriptor(), db.getName()),
               usageStatisticsService.getDatasourceUsageCount(db)))
         .collect(toMap(Entry::getKey, Entry::getValue, (val1, val2) -> val2, LinkedHashMap::new));
   }

}
