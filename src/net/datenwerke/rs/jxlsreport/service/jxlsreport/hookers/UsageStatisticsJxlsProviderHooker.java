package net.datenwerke.rs.jxlsreport.service.jxlsreport.hookers;

import static java.util.stream.Collectors.toMap;

import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReport;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReportVariant;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.locale.JxlsReportMessages;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.hooks.UsageStatisticsEntryProviderHook;

public class UsageStatisticsJxlsProviderHooker implements UsageStatisticsEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String JXLS = "JXLS";
   private final static String JXLS_VAR = "JXLS_VAR";
   
   @Inject
   public UsageStatisticsJxlsProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      final ImmutablePair<Long, Long> reportCount = usageStatisticsService.getSpecificReportCount(JxlsReport.class,
            JxlsReportVariant.class);
      return Stream
            .of(new SimpleEntry<>(ImmutablePair.of(JXLS, JxlsReportMessages.INSTANCE.jxlsReports()),
                  reportCount.getLeft()),
                  new SimpleEntry<>(ImmutablePair.of(JXLS_VAR, JxlsReportMessages.INSTANCE.jxlsReportVariants()),
                        reportCount.getRight()))
            .collect(toMap(Entry::getKey, Entry::getValue, (val1, val2) -> val2, LinkedHashMap::new));
   }

}
