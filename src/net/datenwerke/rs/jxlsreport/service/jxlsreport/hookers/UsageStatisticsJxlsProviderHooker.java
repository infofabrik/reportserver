package net.datenwerke.rs.jxlsreport.service.jxlsreport.hookers;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.base.service.hooks.UsageStatisticsReportEntryProviderHook;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReport;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReportVariant;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.locale.JxlsReportMessages;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class UsageStatisticsJxlsProviderHooker implements UsageStatisticsReportEntryProviderHook {

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
      return usageStatisticsService.provideReportCountValueEntry(JXLS, JxlsReportMessages.INSTANCE.jxlsReports(),
            JxlsReport.class, JXLS_VAR, JxlsReportMessages.INSTANCE.jxlsReportVariants(), JxlsReportVariant.class);
   }

}
