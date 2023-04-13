package net.datenwerke.rs.amazons3.service.amazons3.hooker;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.amazons3.service.amazons3.definitions.AmazonS3Datasink;
import net.datenwerke.rs.amazons3.service.amazons3.locale.AmazonS3DatasinkMessages;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.UsageStatisticsDatasinkEntryProviderHook;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class UsageStatisticsProviderHooker implements UsageStatisticsDatasinkEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String TYPE = "AMAZON_S3_DATASINKS";
   
   @Inject
   public UsageStatisticsProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      return usageStatisticsService.provideNodeCountValueEntry(TYPE,
            AmazonS3DatasinkMessages.INSTANCE.amazonS3Datasinks(), AmazonS3Datasink.class);
   }

}
