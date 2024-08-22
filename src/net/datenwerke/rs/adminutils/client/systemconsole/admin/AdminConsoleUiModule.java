package net.datenwerke.rs.adminutils.client.systemconsole.admin;

import com.google.gwt.inject.client.AbstractGinModule;

public class AdminConsoleUiModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(AdminConsoleUiStartup.class).asEagerSingleton();
   }

}
