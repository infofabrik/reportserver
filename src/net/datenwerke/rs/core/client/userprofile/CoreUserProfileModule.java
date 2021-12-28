package net.datenwerke.rs.core.client.userprofile;

import com.google.gwt.inject.client.AbstractGinModule;

public class CoreUserProfileModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(CoreUserProfileStartup.class).asEagerSingleton();
   }

}
