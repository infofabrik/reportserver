package net.datenwerke.rs.grideditor.client.grideditor;

import com.google.gwt.inject.client.AbstractGinModule;

public class GridEditorUiModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(GridEditorUiStartup.class).asEagerSingleton();
   }

}
