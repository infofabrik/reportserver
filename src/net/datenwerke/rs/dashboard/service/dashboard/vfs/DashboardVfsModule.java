package net.datenwerke.rs.dashboard.service.dashboard.vfs;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class DashboardVfsModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
      bind(DashboardVfsStartup.class).asEagerSingleton();
   }

}
