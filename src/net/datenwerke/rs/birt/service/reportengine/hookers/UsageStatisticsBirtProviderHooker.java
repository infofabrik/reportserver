package net.datenwerke.rs.birt.service.reportengine.hookers;

import static java.util.stream.Collectors.toMap;

import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.birt.service.reportengine.entities.BirtReport;
import net.datenwerke.rs.birt.service.reportengine.entities.BirtReportVariant;
import net.datenwerke.rs.birt.service.reportengine.locale.BirtEngineMessages;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.hooks.UsageStatisticsEntryProviderHook;

public class UsageStatisticsBirtProviderHooker implements UsageStatisticsEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String BIRT = "BIRT";
   private final static String BIRT_VAR = "BIRT_VAR";
   
   @Inject
   public UsageStatisticsBirtProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      final ImmutablePair<Long, Long> reportCount = usageStatisticsService.getSpecificReportCount(BirtReport.class,
            BirtReportVariant.class);
      return Stream
            .of(new SimpleEntry<>(ImmutablePair.of(BIRT, BirtEngineMessages.INSTANCE.birtReports()),
                  reportCount.getLeft()),
                  new SimpleEntry<>(ImmutablePair.of(BIRT_VAR, BirtEngineMessages.INSTANCE.birtReportVariants()),
                        reportCount.getRight()))
            .collect(toMap(Entry::getKey, Entry::getValue, (val1, val2) -> val2, LinkedHashMap::new));
   }

}
