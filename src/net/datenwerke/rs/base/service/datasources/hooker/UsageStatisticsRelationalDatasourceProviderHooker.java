package net.datenwerke.rs.base.service.datasources.hooker;

import static java.util.stream.Collectors.toMap;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.base.service.datasources.locale.DatasourcesMessages;
import net.datenwerke.rs.core.service.datasourcemanager.hooks.UsageStatisticsDatasourceEntryProviderHook;
import net.datenwerke.rs.dsbundle.service.dsbundle.entities.DatabaseBundle;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class UsageStatisticsRelationalDatasourceProviderHooker implements UsageStatisticsDatasourceEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String TYPE = "RELATIONAL_DATASOURCES";
   
   @Inject
   public UsageStatisticsRelationalDatasourceProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      long relational = usageStatisticsService.getNodeCount(DatabaseDatasource.class);
      long bundle = usageStatisticsService.getNodeCount(DatabaseBundle.class);
      return Stream
            .of(new SimpleEntry<>(ImmutablePair.of(TYPE, DatasourcesMessages.INSTANCE.relationalDatasources()),
                  relational - bundle))
            .collect(toMap(Entry::getKey, Entry::getValue, (val1, val2) -> val2, LinkedHashMap::new));
   }

}
