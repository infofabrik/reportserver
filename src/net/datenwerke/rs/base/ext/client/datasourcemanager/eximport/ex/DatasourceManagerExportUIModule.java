package net.datenwerke.rs.base.ext.client.datasourcemanager.eximport.ex;

import com.google.gwt.inject.client.AbstractGinModule;

public class DatasourceManagerExportUIModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(DatasourceManagerExportUIStartup.class).asEagerSingleton();
   }

}
