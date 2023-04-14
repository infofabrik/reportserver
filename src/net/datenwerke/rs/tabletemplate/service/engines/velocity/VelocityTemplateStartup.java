package net.datenwerke.rs.tabletemplate.service.engines.velocity;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.tabletemplate.service.engines.velocity.hookers.UsageStatisticsVelocityTemplateProviderHooker;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.hooks.UsageStatisticsTemplateEntryProviderHook;

public class VelocityTemplateStartup {

   @Inject
   public VelocityTemplateStartup(
         final HookHandlerService hookHandler,
         final Provider<UsageStatisticsVelocityTemplateProviderHooker> usageStats
         ) {
      hookHandler.attachHooker(UsageStatisticsTemplateEntryProviderHook.class, usageStats,
            HookHandlerService.PRIORITY_LOW + 5);
   }
}
