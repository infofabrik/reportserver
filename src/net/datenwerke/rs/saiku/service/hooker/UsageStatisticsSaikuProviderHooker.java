package net.datenwerke.rs.saiku.service.hooker;

import static java.util.stream.Collectors.toMap;

import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.saiku.service.locale.SaikuMessages;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReportVariant;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.hooks.UsageStatisticsEntryProviderHook;

public class UsageStatisticsSaikuProviderHooker implements UsageStatisticsEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String SAIKU = "SAIKU";
   private final static String SAIKU_VAR = "SAIKU_VAR";
   
   @Inject
   public UsageStatisticsSaikuProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      final ImmutablePair<Long, Long> reportCount = usageStatisticsService.getSpecificReportCount(SaikuReport.class,
            SaikuReportVariant.class);
      return Stream.of(
            new SimpleEntry<>(ImmutablePair.of(SAIKU, SaikuMessages.INSTANCE.saikuReports()), reportCount.getLeft()),
            new SimpleEntry<>(ImmutablePair.of(SAIKU_VAR, SaikuMessages.INSTANCE.saikuReportVariants()),
                  reportCount.getRight()))
            .collect(toMap(Entry::getKey, Entry::getValue, (val1, val2) -> val2, LinkedHashMap::new));
   }

}
