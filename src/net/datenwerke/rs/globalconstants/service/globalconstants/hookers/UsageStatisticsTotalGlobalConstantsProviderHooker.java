package net.datenwerke.rs.globalconstants.service.globalconstants.hookers;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.globalconstants.service.globalconstants.entities.GlobalConstant;
import net.datenwerke.rs.globalconstants.service.globalconstants.hooks.GlobalConstantsEntryProviderHook;
import net.datenwerke.rs.globalconstants.service.globalconstants.locale.GlobalConstantsMessages;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class UsageStatisticsTotalGlobalConstantsProviderHooker implements GlobalConstantsEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String TYPE = "TOTAL_GLOBAL_CONSTANTS";
   
   @Inject
   public UsageStatisticsTotalGlobalConstantsProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      return usageStatisticsService.provideNodeCountValueEntry(TYPE,
            GlobalConstantsMessages.INSTANCE.totalGlobalConstants(), GlobalConstant.class);
   }

}
