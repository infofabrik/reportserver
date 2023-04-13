package net.datenwerke.rs.grideditor.service.grideditor;

import com.google.inject.AbstractModule;

public class GridEditorModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(GridEditorStartup.class).asEagerSingleton();
   }

}
