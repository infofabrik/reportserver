package net.datenwerke.rs.globalconstants.service.globalconstants.hookers;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.inject.Provider;

import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.globalconstants.service.globalconstants.hooks.GlobalConstantsEntryProviderHook;
import net.datenwerke.rs.globalconstants.service.globalconstants.locale.GlobalConstantsMessages;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class GlobalConstantCategoryProviderHooker implements GeneralInfoCategoryProviderHook {

   private final Provider<UsageStatisticsService> usageStatisticsServiceProvider;
   
   private final static String TYPE = "GLOBAL_CONSTANTS_USAGE_STATISTICS";
   
   @Inject
   public GlobalConstantCategoryProviderHooker(
         Provider<UsageStatisticsService> usageStatisticsServiceProvider
         ) {
      this.usageStatisticsServiceProvider = usageStatisticsServiceProvider;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Map<ImmutablePair<String, String>, Object>> provideCategory() {
      return usageStatisticsServiceProvider.get().provideCategory(TYPE,
            GlobalConstantsMessages.INSTANCE.globalConstants(), GlobalConstantsEntryProviderHook.class);
   }
}
