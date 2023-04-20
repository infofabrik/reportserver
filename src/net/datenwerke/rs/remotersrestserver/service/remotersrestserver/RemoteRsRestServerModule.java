package net.datenwerke.rs.remotersrestserver.service.remotersrestserver;

import com.google.inject.AbstractModule;

import net.datenwerke.rs.remotersrestserver.service.remotersrestserver.entities.RemoteRsRestServer;

public class RemoteRsRestServerModule extends AbstractModule {

   @Override
   protected void configure() {
      
      requestStaticInjection(RemoteRsRestServer.class);
      
      bind(RemoteRsRestServerStartup.class).asEagerSingleton();
   }

}
