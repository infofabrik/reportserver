package net.datenwerke.rs.core.service.genrights.remoteservers;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class GenRightsRemoteServerManagerModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
      bind(GenRightsRemoteServerManagerStartup.class).asEagerSingleton();
   }

}
