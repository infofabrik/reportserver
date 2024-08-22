package net.datenwerke.rs.remoteserver.client.remoteservermanager.eximport.ex;

import com.google.gwt.inject.client.AbstractGinModule;

public class RemoteServerManagerExportUIModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(RemoteServerManagerExportUIStartup.class).asEagerSingleton();
   }

}