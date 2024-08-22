package net.datenwerke.rs.tabletemplate.service.engines.jxls.hookers;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.tabletemplate.client.engines.jxls.JXlsTemplateUiModule;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.hooks.UsageStatisticsTemplateEntryProviderHook;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class UsageStatisticsJxlsTemplateProviderHooker implements UsageStatisticsTemplateEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String TYPE = "JXLS_TEMPLATES";
   
   @Inject
   public UsageStatisticsJxlsTemplateProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      return usageStatisticsService.provideTableTemplateCountValueEntry(TYPE,
            "JXLS", JXlsTemplateUiModule.TEMPLATE_TYPE);
   }

}
