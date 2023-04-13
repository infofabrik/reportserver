package net.datenwerke.rs.remoteserver.service.remoteservermanager.hookers;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.inject.Provider;

import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.hooks.RemoteServerEntryProviderHook;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.locale.RemoteServerManagerMessages;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class RemoteServerCategoryProviderHooker implements GeneralInfoCategoryProviderHook {

   private final Provider<UsageStatisticsService> usageStatisticsServiceProvider;
   
   private final static String TYPE = "REMOTE_SERVER_USAGE_STATISTICS";
   
   @Inject
   public RemoteServerCategoryProviderHooker(
         Provider<UsageStatisticsService> usageStatisticsServiceProvider
         ) {
      this.usageStatisticsServiceProvider = usageStatisticsServiceProvider;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Map<ImmutablePair<String, String>, Object>> provideCategory() {
      return usageStatisticsServiceProvider.get().provideCategory(TYPE,
            RemoteServerManagerMessages.INSTANCE.remoteServerUsageStatistics(), RemoteServerEntryProviderHook.class);
   }
}
