package net.datenwerke.rs.emaildatasink.service.emaildatasink.hooker;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.core.service.datasinkmanager.hooks.UsageStatisticsDatasinkEntryProviderHook;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.definitions.EmailDatasink;
import net.datenwerke.rs.emaildatasink.service.emaildatasink.locale.EmailDatasinkMessages;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class UsageStatisticsProviderHooker implements UsageStatisticsDatasinkEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String TYPE = "EMAIL_DATASINKS";
   
   @Inject
   public UsageStatisticsProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      return usageStatisticsService.provideNodeCountValueEntry(TYPE,
            EmailDatasinkMessages.INSTANCE.emailDatasinks(), EmailDatasink.class);
   }

}
