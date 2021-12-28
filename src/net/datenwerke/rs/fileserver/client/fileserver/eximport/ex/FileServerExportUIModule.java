package net.datenwerke.rs.fileserver.client.fileserver.eximport.ex;

import com.google.gwt.inject.client.AbstractGinModule;

public class FileServerExportUIModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(FileServerExportUIStartup.class).asEagerSingleton();
   }

}
