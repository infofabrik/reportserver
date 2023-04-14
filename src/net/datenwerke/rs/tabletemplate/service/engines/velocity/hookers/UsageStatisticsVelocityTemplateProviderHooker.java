package net.datenwerke.rs.tabletemplate.service.engines.velocity.hookers;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.remoteserver.service.remoteservermanager.hooks.RemoteServerEntryProviderHook;
import net.datenwerke.rs.tabletemplate.client.engines.velocity.VelocityTemplateUIModule;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class UsageStatisticsVelocityTemplateProviderHooker implements RemoteServerEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String TYPE = "VELOCITY_TEMPLATES";
   
   @Inject
   public UsageStatisticsVelocityTemplateProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      return usageStatisticsService.provideTableTemplateCountValueEntry(TYPE,
            "Velocity", VelocityTemplateUIModule.TEMPLATE_TYPE);
   }

}
