package net.datenwerke.rs.base.ext.service.reportmanager.vfs;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class ReportManagerVFSModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
      bind(ReportManagerVFSStartup.class).asEagerSingleton();
   }

}
