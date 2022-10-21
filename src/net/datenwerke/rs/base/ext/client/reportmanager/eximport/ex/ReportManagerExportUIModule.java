package net.datenwerke.rs.base.ext.client.reportmanager.eximport.ex;

import com.google.gwt.inject.client.AbstractGinModule;

public class ReportManagerExportUIModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(ReportManagerExportUIStartup.class).asEagerSingleton();
   }

}
