package net.datenwerke.rs.base.service.reportengines.hookers;

import static java.util.stream.Collectors.toMap;

import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.base.service.reportengines.locale.ReportEnginesMessages;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReportVariant;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.hooks.UsageStatisticsEntryProviderHook;

public class UsageStatisticsDynaListProviderHooker implements UsageStatisticsEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String DYNAMIC_LISTS = "DYNAMIC_LISTS";
   private final static String DYNAMIC_LIST_VAR = "DYNAMIC_LIST_VAR";
   
   @Inject
   public UsageStatisticsDynaListProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      final ImmutablePair<Long, Long> reportCount = usageStatisticsService.getSpecificReportCount(TableReport.class,
            TableReportVariant.class);
      return Stream
            .of(new SimpleEntry<>(ImmutablePair.of(DYNAMIC_LISTS, ReportEnginesMessages.INSTANCE.dynamicLists()),
                  reportCount.getLeft()),
                  new SimpleEntry<>(
                        ImmutablePair.of(DYNAMIC_LIST_VAR, ReportEnginesMessages.INSTANCE.dynamicListVariants()),
                        reportCount.getRight()))
            .collect(toMap(Entry::getKey, Entry::getValue, (val1, val2) -> val2, LinkedHashMap::new));
   }

}
