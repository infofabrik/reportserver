package net.datenwerke.gf.service.properties;

import com.google.inject.AbstractModule;

public class PropertiesModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(PropertiesService.class).to(PropertiesServiceImpl.class).asEagerSingleton();
   }

}
