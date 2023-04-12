package net.datenwerke.rs.remotersserver.service.remotersserver;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.remotersserver.service.remotersserver.hookers.UsageStatisticsRemoteRsServersProviderHooker;
import net.datenwerke.rs.usagestatistics.service.usagestatistics.hooks.UsageStatisticsEntryProviderHook;

public class RemoteRsServerStartup {

   @Inject
   public RemoteRsServerStartup(
         final HookHandlerService hookHandler,

         final Provider<UsageStatisticsRemoteRsServersProviderHooker> usageStatsRemoteRsServerProvider
         ) {

      hookHandler.attachHooker(UsageStatisticsEntryProviderHook.class, usageStatsRemoteRsServerProvider,
            HookHandlerService.PRIORITY_LOW + 95);
   }
}
