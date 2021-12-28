package net.datenwerke.rs.computedcolumns.client.computedcolumns;

import com.google.gwt.inject.client.AbstractGinModule;

public class ComputedColumnsUiModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(ComputedColumnsUiStartup.class).asEagerSingleton();
   }

}
