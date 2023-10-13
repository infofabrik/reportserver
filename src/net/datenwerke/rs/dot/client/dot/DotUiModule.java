package net.datenwerke.rs.dot.client.dot;

import com.google.gwt.inject.client.AbstractGinModule;

public class DotUiModule extends AbstractGinModule {

   public static final String MIME_TYPE = "text/vnd.graphviz";
   
   @Override
   protected void configure() {
      bind(DotUiStartup.class).asEagerSingleton();
   }

}
