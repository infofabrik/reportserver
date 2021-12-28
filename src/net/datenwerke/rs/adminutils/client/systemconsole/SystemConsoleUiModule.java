package net.datenwerke.rs.adminutils.client.systemconsole;

import com.google.gwt.inject.client.AbstractGinModule;

import net.datenwerke.rs.adminutils.client.systemconsole.connpool.ConnectionPoolConsoleUiModule;
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.GeneralInfoUiModule;
import net.datenwerke.rs.adminutils.client.systemconsole.memory.MemoryConsoleUiModule;

public class SystemConsoleUiModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(SystemConsoleUiStartup.class).asEagerSingleton();

      install(new GeneralInfoUiModule());
      install(new MemoryConsoleUiModule());
      install(new ConnectionPoolConsoleUiModule());
   }

}
