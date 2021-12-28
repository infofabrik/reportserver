package net.datenwerke.rs.adminutils.client.suuser;

import com.google.gwt.inject.client.AbstractGinModule;

public class SuUserUIModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(SuUserUIStartup.class).asEagerSingleton();
   }

}
