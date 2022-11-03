package net.datenwerke.rs.rest.service.rest.usermanager;

import com.google.inject.AbstractModule;

public class RestUserManagerModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(RestUserManagerStartup.class).asEagerSingleton();
   }
   
}
