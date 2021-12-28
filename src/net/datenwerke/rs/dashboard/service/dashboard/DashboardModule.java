package net.datenwerke.rs.dashboard.service.dashboard;

import com.google.inject.AbstractModule;

import net.datenwerke.rs.dashboard.service.dashboard.genrights.GenRightsDashboardModule;
import net.datenwerke.rs.dashboard.service.dashboard.vfs.DashboardVfsModule;

public class DashboardModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(DashboardService.class).to(DashboardServiceImpl.class);
      bind(DadgetService.class).to(DadgetServiceImpl.class);
      bind(DashboardManagerService.class).to(DashboardManagerServiceImpl.class);

      bind(DashboardStartup.class).asEagerSingleton();

      install(new DashboardVfsModule());
      install(new GenRightsDashboardModule());
   }

}
