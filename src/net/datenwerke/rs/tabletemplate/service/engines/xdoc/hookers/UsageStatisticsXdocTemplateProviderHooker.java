package net.datenwerke.rs.tabletemplate.service.engines.xdoc.hookers;

import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.remoteserver.service.remoteservermanager.hooks.RemoteServerEntryProviderHook;
import net.datenwerke.rs.tabletemplate.client.engines.xdoc.XdocTemplateUIModule;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.UsageStatisticsService;

public class UsageStatisticsXdocTemplateProviderHooker implements RemoteServerEntryProviderHook {

   private final UsageStatisticsService usageStatisticsService;
   
   private final static String TYPE = "XDOC_TEMPLATES";
   
   @Inject
   public UsageStatisticsXdocTemplateProviderHooker(
         UsageStatisticsService usageStatisticsService
         ) {
      this.usageStatisticsService = usageStatisticsService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      return usageStatisticsService.provideTableTemplateCountValueEntry(TYPE,
            "XDOC", XdocTemplateUIModule.TEMPLATE_TYPE);
   }

}
