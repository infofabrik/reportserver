package net.datenwerke.rs.birt.service.reportengine.hookers;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.base.service.hooks.UsageStatisticsReportEntryProviderHook;
import net.datenwerke.rs.birt.service.reportengine.entities.BirtReport;
import net.datenwerke.rs.birt.service.reportengine.entities.BirtReportVariant;
import net.datenwerke.rs.birt.service.reportengine.locale.BirtEngineMessages;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class UsageStatisticsBirtProviderHooker implements UsageStatisticsReportEntryProviderHook {

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
      return usageStatisticsService.provideReportCountValueEntry(BIRT, BirtEngineMessages.INSTANCE.birtReports(),
            BirtReport.class, BIRT_VAR, BirtEngineMessages.INSTANCE.birtReportVariants(), BirtReportVariant.class);
   }

}
