package net.datenwerke.rs.adminutils.service.systemconsole.admin;

import com.google.inject.AbstractModule;

public class AdminConsoleModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(AdminConsoleService.class).to(AdminConsoleServiceImpl.class);
   }

}
