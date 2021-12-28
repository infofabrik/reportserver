package net.datenwerke.rs.base.ext.service.parameters.fileselection;

import com.google.inject.AbstractModule;

public class FileSelectionParameterModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(FileSelectionParameterService.class).to(FileSelectionParameterServiceImpl.class);

      bind(FileSelectionParameterStartup.class).asEagerSingleton();
   }

}
