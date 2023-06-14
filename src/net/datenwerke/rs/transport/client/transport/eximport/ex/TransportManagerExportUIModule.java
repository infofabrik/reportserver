package net.datenwerke.rs.transport.client.transport.eximport.ex;

import com.google.gwt.inject.client.AbstractGinModule;

public class TransportManagerExportUIModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(TransportManagerExportUIStartup.class).asEagerSingleton();
   }

}