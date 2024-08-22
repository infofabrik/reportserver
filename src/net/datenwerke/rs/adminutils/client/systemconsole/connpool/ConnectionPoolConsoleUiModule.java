package net.datenwerke.rs.adminutils.client.systemconsole.connpool;

import com.google.gwt.inject.client.AbstractGinModule;

public class ConnectionPoolConsoleUiModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(ConnectionPoolConsoleUiStartup.class).asEagerSingleton();
   }

}
