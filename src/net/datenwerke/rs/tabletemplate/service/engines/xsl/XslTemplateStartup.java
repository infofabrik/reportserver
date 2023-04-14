package net.datenwerke.rs.tabletemplate.service.engines.xsl;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.tabletemplate.service.engines.xsl.hookers.UsageStatisticsXslTemplateProviderHooker;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.hooks.UsageStatisticsTemplateEntryProviderHook;

public class XslTemplateStartup {

   @Inject
   public XslTemplateStartup(
         final HookHandlerService hookHandler,
         final Provider<UsageStatisticsXslTemplateProviderHooker> usageStats
         ) {
      hookHandler.attachHooker(UsageStatisticsTemplateEntryProviderHook.class, usageStats,
            HookHandlerService.PRIORITY_LOW + 15);
   }
}
