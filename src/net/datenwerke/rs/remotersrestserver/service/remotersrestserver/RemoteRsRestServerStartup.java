package net.datenwerke.rs.remotersrestserver.service.remotersrestserver;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.lifecycle.hooks.ConfigDoneHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.remotersrestserver.service.remotersrestserver.entities.RemoteRsRestServer;
import net.datenwerke.rs.remotersrestserver.service.remotersrestserver.hookers.UsageStatisticsRemoteRsServersProviderHooker;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.hooks.RemoteServerEntryProviderHook;
import net.datenwerke.security.service.security.SecurityService;

public class RemoteRsRestServerStartup {

   @Inject
   public RemoteRsRestServerStartup(
         final HookHandlerService hookHandler,
         final Provider<SecurityService> securityServiceProvider,
         final Provider<UsageStatisticsRemoteRsServersProviderHooker> usageStatsRemoteRsServerProvider
         ) {

      hookHandler.attachHooker(RemoteServerEntryProviderHook.class, usageStatsRemoteRsServerProvider,
            HookHandlerService.PRIORITY_LOW + 5);
      
      /* register security targets */
      hookHandler
            .attachHooker(ConfigDoneHook.class, () -> securityServiceProvider.get().registerSecurityTarget(RemoteRsRestServer.class));
   }
}
