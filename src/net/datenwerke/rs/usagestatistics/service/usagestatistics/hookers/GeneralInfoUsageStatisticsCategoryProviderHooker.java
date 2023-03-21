package net.datenwerke.rs.usagestatistics.service.usagestatistics.hookers;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.hooks.UsageStatisticsEntryProviderHook;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.locale.UsageStatisticsMessages;

public class GeneralInfoUsageStatisticsCategoryProviderHooker implements GeneralInfoCategoryProviderHook {

   private final Provider<HookHandlerService> hookHandlerProvider;
   
   private final static String USAGE_STATISTICS = "USAGE_STATISTICS";
   
   @Inject
   public GeneralInfoUsageStatisticsCategoryProviderHooker(
         Provider<HookHandlerService> hookHandlerProvider
         ) {
      this.hookHandlerProvider = hookHandlerProvider;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Map<ImmutablePair<String, String>, Object>> provideCategory() {
      return Collections.singletonMap(ImmutablePair.of(USAGE_STATISTICS, UsageStatisticsMessages.INSTANCE.usageStatistics()), 
         hookHandlerProvider.get().getHookers(UsageStatisticsEntryProviderHook.class)
            .stream()
            .map(UsageStatisticsEntryProviderHook::provideEntry)
            .reduce(new LinkedHashMap<>(), (into, valuesToAdd) -> {
               into.putAll(valuesToAdd);
               return into;
            }));
   }
   

}
