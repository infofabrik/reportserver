package net.datenwerke.rs.saiku.service.hooker;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.base.service.hooks.UsageStatisticsReportEntryProviderHook;
import net.datenwerke.rs.saiku.service.locale.SaikuMessages;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReportVariant;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class UsageStatisticsSaikuProviderHooker implements UsageStatisticsReportEntryProviderHook {

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
      return usageStatisticsService.provideReportCountValueEntry(SAIKU, SaikuMessages.INSTANCE.saikuReports(),
            SaikuReport.class, SAIKU_VAR, SaikuMessages.INSTANCE.saikuReportVariants(), SaikuReportVariant.class);
   }

}
