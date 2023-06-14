package net.datenwerke.rs.core.service.genrights.transport;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class GenRightsTransportManagerModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
      bind(GenRightsTransportManagerStartup.class).asEagerSingleton();
   }

}
