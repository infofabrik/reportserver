package net.datenwerke.rs.incubator.service.aliascmd;

import com.google.inject.AbstractModule;

public class AliasCmdModule extends AbstractModule {

   public static final String CONFIG_FILE = "terminal/alias.cf";

   @Override
   protected void configure() {
      bind(AliasCmdStartup.class).asEagerSingleton();
   }

}
