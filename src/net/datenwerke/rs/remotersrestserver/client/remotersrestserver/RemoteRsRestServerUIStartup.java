package net.datenwerke.rs.remotersrestserver.client.remotersrestserver;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.remotersrestserver.client.remotersrestserver.hookers.RemoteServerConfigProviderHooker;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.hooks.RemoteServerDefinitionConfigProviderHook;

public class RemoteRsRestServerUIStartup {

   @Inject
   public RemoteRsRestServerUIStartup(
         final HookHandlerService hookHandler,
         final Provider<RemoteServerConfigProviderHooker> remoteServerConfigProviderHooker
         ) {

      hookHandler.attachHooker(RemoteServerDefinitionConfigProviderHook.class, remoteServerConfigProviderHooker.get());
   }

}
