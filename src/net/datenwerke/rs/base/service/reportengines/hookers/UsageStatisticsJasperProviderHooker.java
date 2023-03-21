package net.datenwerke.rs.base.service.reportengines.hookers;

import static java.util.stream.Collectors.toMap;

import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReportVariant;
import net.datenwerke.rs.base.service.reportengines.locale.ReportEnginesMessages;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.hooks.UsageStatisticsEntryProviderHook;

public class UsageStatisticsJasperProviderHooker implements UsageStatisticsEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String JASPER = "JASPER";
   private final static String JASPER_VAR = "JASPER_VAR";
   
   @Inject
   public UsageStatisticsJasperProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      final ImmutablePair<Long, Long> reportCount = usageStatisticsService.getSpecificReportCount(JasperReport.class,
            JasperReportVariant.class);
      return Stream.of(
            new SimpleEntry<>(ImmutablePair.of(JASPER, ReportEnginesMessages.INSTANCE.jasperReports()),
                  reportCount.getLeft()),
            new SimpleEntry<>(ImmutablePair.of(JASPER_VAR, ReportEnginesMessages.INSTANCE.jasperReportVariants()),
                  reportCount.getRight()))
            .collect(toMap(Entry::getKey, Entry::getValue, (val1, val2) -> val2, LinkedHashMap::new));
   }

}
