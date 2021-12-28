package net.datenwerke.rs.license.client;

import com.google.gwt.inject.client.AbstractGinModule;

public class LicenseUiModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(LicenseUiStartup.class).asEagerSingleton();
   }

}
