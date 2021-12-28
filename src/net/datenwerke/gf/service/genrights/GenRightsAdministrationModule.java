package net.datenwerke.gf.service.genrights;

import com.google.inject.AbstractModule;

public class GenRightsAdministrationModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(GenRightsAdministrationStartup.class).asEagerSingleton();
   }

}
