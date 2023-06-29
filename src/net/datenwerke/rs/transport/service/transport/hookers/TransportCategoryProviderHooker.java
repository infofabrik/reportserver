package net.datenwerke.rs.transport.service.transport.hookers;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.inject.Provider;

import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.transport.service.transport.hooks.TransportEntryProviderHook;
import net.datenwerke.rs.transport.service.transport.locale.TransportManagerMessages;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class TransportCategoryProviderHooker implements GeneralInfoCategoryProviderHook {

   private final Provider<UsageStatisticsService> usageStatisticsServiceProvider;
   
   private final static String TYPE = "TRANSPORT_USAGE_STATISTICS";
   
   @Inject
   public TransportCategoryProviderHooker(
         Provider<UsageStatisticsService> usageStatisticsServiceProvider
         ) {
      this.usageStatisticsServiceProvider = usageStatisticsServiceProvider;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Map<ImmutablePair<String, String>, Object>> provideCategory() {
      return usageStatisticsServiceProvider.get().provideCategory(TYPE,
            TransportManagerMessages.INSTANCE.transportUsageStatistics(),
            TransportEntryProviderHook.class);
   }
}
