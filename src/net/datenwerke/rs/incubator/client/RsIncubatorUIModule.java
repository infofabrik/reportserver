package net.datenwerke.rs.incubator.client;

import com.google.gwt.inject.client.AbstractGinModule;

import net.datenwerke.rs.incubator.client.globalsearch.GlobalSearchUiModule;
import net.datenwerke.rs.incubator.client.managerhelpersearch.ManagerHelperSearchUiModule;
import net.datenwerke.rs.incubator.client.scriptdatasource.ScriptDatasourceUiModule;

public class RsIncubatorUIModule extends AbstractGinModule {

   @Override
   protected void configure() {
      install(new ManagerHelperSearchUiModule());
      install(new GlobalSearchUiModule());
      install(new ScriptDatasourceUiModule());
   }

}
