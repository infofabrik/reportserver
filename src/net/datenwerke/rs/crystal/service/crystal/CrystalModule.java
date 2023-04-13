package net.datenwerke.rs.crystal.service.crystal;

import com.google.inject.AbstractModule;

public class CrystalModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(CrystalStartup.class).asEagerSingleton();
   }

}
