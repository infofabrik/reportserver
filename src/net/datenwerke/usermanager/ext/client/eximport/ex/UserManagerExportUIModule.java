package net.datenwerke.usermanager.ext.client.eximport.ex;

import com.google.gwt.inject.client.AbstractGinModule;

public class UserManagerExportUIModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(UserManagerExportUIStartup.class).asEagerSingleton();
   }

}
