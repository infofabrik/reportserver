package net.datenwerke.rs.search.client.search;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class SearchUiModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(SearchUiService.class).to(SearchUiServiceImpl.class).in(Singleton.class);

      bind(SearchUiStartup.class).asEagerSingleton();
   }

}
