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
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.hooks.UsageStatisticsEntryProviderHook;

public class UsageStatisticsTotalReportProviderHooker implements UsageStatisticsEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String BASE_REPORTS = "TOTAL_BASE_REPORTS";
   private final static String REPORT_VAR = "TOTAL_REPORT_VAR";
   
   @Inject
   public UsageStatisticsTotalReportProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      final ImmutablePair<Long, Long> reportCount = usageStatisticsService.getReportCount();
      
      return Stream
            .of(new SimpleEntry<>(ImmutablePair.of(BASE_REPORTS, ReportEnginesMessages.INSTANCE.totalBaseReports()),
                  reportCount.getLeft()),
                  new SimpleEntry<>(ImmutablePair.of(REPORT_VAR, ReportEnginesMessages.INSTANCE.totalReportVariants()),
                        reportCount.getRight()))
            .collect(toMap(Entry::getKey, Entry::getValue, (val1, val2) -> val2, LinkedHashMap::new));
   }

}
