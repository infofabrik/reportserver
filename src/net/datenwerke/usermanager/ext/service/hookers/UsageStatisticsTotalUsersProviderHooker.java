package net.datenwerke.usermanager.ext.service.hookers;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.security.service.usermanager.hooks.UsageStatisticsUserEntryProviderHook;
import net.datenwerke.usermanager.ext.service.locale.UserManagerMessages;

public class UsageStatisticsTotalUsersProviderHooker implements UsageStatisticsUserEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String TYPE = "TOTAL_USERS";
   
   @Inject
   public UsageStatisticsTotalUsersProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      return usageStatisticsService.provideNodeCountValueEntry(TYPE,
            UserManagerMessages.INSTANCE.totalNumberOfUsers(), User.class);
   }

}
