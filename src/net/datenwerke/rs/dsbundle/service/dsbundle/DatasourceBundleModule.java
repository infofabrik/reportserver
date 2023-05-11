package net.datenwerke.rs.dsbundle.service.dsbundle;

import com.google.inject.AbstractModule;

public class DatasourceBundleModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(DatasourceBundleStartup.class).asEagerSingleton();
   }

}
