package net.datenwerke.rs.base.ext.client.datasinkmanager.eximport.ex;

import com.google.gwt.inject.client.AbstractGinModule;

public class DatasinkManagerExportUIModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(DatasinkManagerExportUIStartup.class).asEagerSingleton();
   }

}