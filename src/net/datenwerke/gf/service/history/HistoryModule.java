package net.datenwerke.gf.service.history;

import com.google.inject.AbstractModule;

public class HistoryModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(HistoryService.class).to(HistoryServiceImpl.class);

      bind(HistoryStartup.class).asEagerSingleton();
   }

}
