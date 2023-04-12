package net.datenwerke.rs.remoteserver.service.remoteservermanager;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.history.hooks.HistoryUrlBuilderHook;
import net.datenwerke.gf.service.lifecycle.hooks.ConfigDoneHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.remotersserver.service.remotersserver.entities.RemoteRsServer;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerFolder;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.history.RemoteServerManagerHistoryUrlBuilderHooker;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.security.SecurityService;

public class RemoteServerStartup {

   @Inject
   public RemoteServerStartup(
         HookHandlerService hookHandler, 
         EventBus eventBus,
         final Provider<SecurityService> securityServiceProvider,
         Provider<RemoteServerManagerHistoryUrlBuilderHooker> remoteServerManagerUrlBuilder
         ) {

      /* history */
      hookHandler.attachHooker(HistoryUrlBuilderHook.class, remoteServerManagerUrlBuilder);
      
      /* register security targets */
      hookHandler.attachHooker(ConfigDoneHook.class, new ConfigDoneHook() {

         @Override
         public void configDone() {
            /* secure folder */
            securityServiceProvider.get().registerSecurityTarget(RemoteServerFolder.class);
            /* secure object */
            securityServiceProvider.get().registerSecurityTarget(RemoteRsServer.class);
         }
      });
   }
}
