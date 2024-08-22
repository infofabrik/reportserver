package net.datenwerke.rs.demo;

import com.google.inject.AbstractModule;

public class DemoDataModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(DemoDataStartup.class).asEagerSingleton();
   }

}
