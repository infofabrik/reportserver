package net.datenwerke.security.service.genrights.security;

import com.google.inject.AbstractModule;

public class GenRightsSecurityModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(GenRightsSecurityStartup.class).asEagerSingleton();
   }

}
