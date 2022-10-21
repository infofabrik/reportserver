package net.datenwerke.rs.base.client.reportengines.table.prefilter;

import com.google.gwt.inject.client.AbstractGinModule;

public class PreFilterUiModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(PreFilterUiStartup.class).asEagerSingleton();
   }

}
