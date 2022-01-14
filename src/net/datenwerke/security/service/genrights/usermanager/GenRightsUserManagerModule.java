package net.datenwerke.security.service.genrights.usermanager;

import com.google.inject.AbstractModule;

public class GenRightsUserManagerModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(GenRightsUserManagerStartup.class).asEagerSingleton();
   }

}
