package net.datenwerke.rs.base.service.reportengines.hookers;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.base.service.hooks.UsageStatisticsReportEntryProviderHook;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReportVariant;
import net.datenwerke.rs.base.service.reportengines.locale.ReportEnginesMessages;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class UsageStatisticsJasperProviderHooker implements UsageStatisticsReportEntryProviderHook {

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
      return usageStatisticsService.provideReportCountValueEntry(JASPER, ReportEnginesMessages.INSTANCE.jasperReports(),
            JasperReport.class, JASPER_VAR, ReportEnginesMessages.INSTANCE.jasperReportVariants(),
            JasperReportVariant.class);
   }

}
