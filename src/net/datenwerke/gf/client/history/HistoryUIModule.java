package net.datenwerke.gf.client.history;

import com.google.gwt.inject.client.AbstractGinModule;

public class HistoryUIModule extends AbstractGinModule {

   @Override
   protected void configure() {

      bind(HistoryUiService.class).to(HistoryUiServiceImpl.class);
      bind(HistoryUIStartup.class).asEagerSingleton();
   }

}
