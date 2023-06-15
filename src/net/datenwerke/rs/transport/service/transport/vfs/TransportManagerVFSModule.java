package net.datenwerke.rs.transport.service.transport.vfs;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class TransportManagerVFSModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
      bind(TransportManagerVFSStartup.class).asEagerSingleton();
   }

}
