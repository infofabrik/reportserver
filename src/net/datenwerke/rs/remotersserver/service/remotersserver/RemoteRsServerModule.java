package net.datenwerke.rs.remotersserver.service.remotersserver;

import com.google.inject.AbstractModule;

public class RemoteRsServerModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(RemoteRsServerStartup.class).asEagerSingleton();
   }

}
