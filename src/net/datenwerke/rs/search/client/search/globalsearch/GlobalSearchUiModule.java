package net.datenwerke.rs.search.client.search.globalsearch;

import com.google.gwt.inject.client.AbstractGinModule;

public class GlobalSearchUiModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(GlobalSearchUiStartup.class).asEagerSingleton();
   }

}
