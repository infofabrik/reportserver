package net.datenwerke.rs.base.ext.client;

import com.google.gwt.inject.client.AbstractGinModule;

import net.datenwerke.rs.base.ext.client.dashboardmanager.eximport.ex.DashboardManagerExportUIModule;
import net.datenwerke.rs.base.ext.client.datasinkmanager.eximport.ex.DatasinkManagerExportUIModule;
import net.datenwerke.rs.base.ext.client.datasourcemanager.eximport.ex.DatasourceManagerExportUIModule;
import net.datenwerke.rs.base.ext.client.reportmanager.eximport.ex.ReportManagerExportUIModule;

public class RsBaseExtUiModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(RsBaseExtUiStartup.class).asEagerSingleton();

      /* sub modules */
      install(new DatasourceManagerExportUIModule());
      install(new ReportManagerExportUIModule());
      install(new DashboardManagerExportUIModule());
      install(new DatasinkManagerExportUIModule());
   }

}
