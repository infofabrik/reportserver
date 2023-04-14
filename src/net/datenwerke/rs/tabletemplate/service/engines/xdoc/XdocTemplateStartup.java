package net.datenwerke.rs.tabletemplate.service.engines.xdoc;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.tabletemplate.service.engines.xdoc.hookers.UsageStatisticsXdocTemplateProviderHooker;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.hooks.UsageStatisticsTemplateEntryProviderHook;

public class XdocTemplateStartup {

   @Inject
   public XdocTemplateStartup(
         final HookHandlerService hookHandler,
         final Provider<UsageStatisticsXdocTemplateProviderHooker> usageStats
         ) {
      hookHandler.attachHooker(UsageStatisticsTemplateEntryProviderHook.class, usageStats,
            HookHandlerService.PRIORITY_LOW + 10);
   }
}
