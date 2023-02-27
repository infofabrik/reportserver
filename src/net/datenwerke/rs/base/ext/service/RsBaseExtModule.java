package net.datenwerke.rs.base.ext.service;

import com.google.inject.AbstractModule;

import net.datenwerke.rs.base.ext.service.datasinkmanager.vfs.DatasinkManagerVFSModule;
import net.datenwerke.rs.base.ext.service.datasourcemanager.vfs.DatasourceManagerVFSModule;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.FileSelectionParameterModule;
import net.datenwerke.rs.base.ext.service.reportmanager.vfs.ReportManagerVFSModule;

public class RsBaseExtModule extends AbstractModule {

   @Override
   protected void configure() {
      install(new ReportManagerVFSModule());
      install(new DatasourceManagerVFSModule());
      install(new DatasinkManagerVFSModule());

      install(new FileSelectionParameterModule());
      
      bind(RemoteEntityImporterService.class).to(RemoteEntityImporterServiceImpl.class);

      bind(RsBaseExtStartup.class).asEagerSingleton();
   }

}
