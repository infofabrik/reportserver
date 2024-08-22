package net.datenwerke.rs.base.ext.service.datasinkmanager.vfs;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class DatasinkManagerVFSModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
      bind(DatasinkManagerVFSStartup.class).asEagerSingleton();
   }

}
