package net.datenwerke.rs.tabletemplate.service.engines.jxls;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.tabletemplate.service.engines.jxls.hookers.UsageStatisticsJxlsTemplateProviderHooker;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.hooks.UsageStatisticsTemplateEntryProviderHook;

public class JxlsTemplateStartup {

   @Inject
   public JxlsTemplateStartup(
         final HookHandlerService hookHandler,
         final Provider<UsageStatisticsJxlsTemplateProviderHooker> usageStats
         ) {
      hookHandler.attachHooker(UsageStatisticsTemplateEntryProviderHook.class, usageStats,
            HookHandlerService.PRIORITY_LOW);
   }
}
