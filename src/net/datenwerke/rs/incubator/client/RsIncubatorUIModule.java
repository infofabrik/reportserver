package net.datenwerke.rs.incubator.client;

import com.google.gwt.inject.client.AbstractGinModule;

import net.datenwerke.rs.incubator.client.managerhelpersearch.ManagerHelperSearchUiModule;
import net.datenwerke.rs.search.client.search.globalsearch.GlobalSearchUiModule;

public class RsIncubatorUIModule extends AbstractGinModule {

   @Override
   protected void configure() {
      install(new ManagerHelperSearchUiModule());
      install(new GlobalSearchUiModule());
   }

}
