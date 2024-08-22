package net.datenwerke.rs.adminutils.service.logs;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class LogFilesModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
      /* startup */
      bind(LogFilesStartup.class).asEagerSingleton();

      bind(LogFilesService.class).to(LogFilesServiceImpl.class);
   }

}
