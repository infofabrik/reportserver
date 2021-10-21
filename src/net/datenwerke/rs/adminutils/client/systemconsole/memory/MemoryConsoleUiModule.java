package net.datenwerke.rs.adminutils.client.systemconsole.memory;

import com.google.gwt.inject.client.AbstractGinModule;

public class MemoryConsoleUiModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(MemoryConsoleUiStartup.class).asEagerSingleton();
   }

}
