package net.datenwerke.rs.scriptdatasource.client.scriptdatasource;

import com.google.gwt.inject.client.AbstractGinModule;

public class ScriptDatasourceUiModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(ScriptDatasourceUiStartup.class).asEagerSingleton();
   }

}
