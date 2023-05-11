package net.datenwerke.rs.scriptdatasource.service.scriptdatasource;

import com.google.inject.AbstractModule;

public class ScriptDatasourceModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(ScriptDatasourceStartup.class).asEagerSingleton();
   }

}
