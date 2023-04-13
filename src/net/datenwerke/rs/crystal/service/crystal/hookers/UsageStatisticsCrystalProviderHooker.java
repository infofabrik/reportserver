package net.datenwerke.rs.crystal.service.crystal.hookers;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.base.service.hooks.UsageStatisticsReportEntryProviderHook;
import net.datenwerke.rs.crystal.service.crystal.entities.CrystalReport;
import net.datenwerke.rs.crystal.service.crystal.entities.CrystalReportVariant;
import net.datenwerke.rs.crystal.service.crystal.locale.CrystalEngineMessages;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class UsageStatisticsCrystalProviderHooker implements UsageStatisticsReportEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String CRYSTAL = "CRYSTAL";
   private final static String CRYSTAL_VAR = "CRYSTAL_VAR";
   
   @Inject
   public UsageStatisticsCrystalProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      return usageStatisticsService.provideReportCountValueEntry(CRYSTAL,
            CrystalEngineMessages.INSTANCE.crystalReports(), CrystalReport.class, CRYSTAL_VAR,
            CrystalEngineMessages.INSTANCE.crystalReportVariants(), CrystalReportVariant.class);
   }

}
