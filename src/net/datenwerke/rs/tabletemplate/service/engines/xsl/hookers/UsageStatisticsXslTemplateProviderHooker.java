package net.datenwerke.rs.tabletemplate.service.engines.xsl.hookers;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.tabletemplate.client.engines.xsl.XslTemplateUIModule;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.hooks.UsageStatisticsTemplateEntryProviderHook;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class UsageStatisticsXslTemplateProviderHooker implements UsageStatisticsTemplateEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String TYPE = "XSL_TEMPLATES";
   
   @Inject
   public UsageStatisticsXslTemplateProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      return usageStatisticsService.provideTableTemplateCountValueEntry(TYPE,
            "XSL", XslTemplateUIModule.TEMPLATE_TYPE);
   }

}
