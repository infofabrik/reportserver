package net.datenwerke.rs.installation;

import com.google.inject.AbstractModule;

public class RsInstallModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(RsInstallStartup.class).asEagerSingleton();
   }

}
