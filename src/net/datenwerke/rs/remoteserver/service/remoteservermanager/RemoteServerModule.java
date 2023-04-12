package net.datenwerke.rs.remoteserver.service.remoteservermanager;

import com.google.inject.Scopes;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;
import net.datenwerke.rs.remotersserver.service.remotersserver.entities.RemoteRsServer;

public class RemoteServerModule extends AbstractReportServerModule {

   public static final String CONFIG_FILE = "remoteservers/remoteservers.cf";
   public static final String PROPERTY_DEFAULT_DISABLED = "[@disabled]";
   public static final String PROPERTY_DEFAULT_SCHEDULING_ENABLED = "[@supportsScheduling]";

   @Override
   protected void configure() {
      bind(RemoteServerTreeService.class).to(RemoteServerTreeServiceImpl.class).in(Scopes.SINGLETON);
      
      requestStaticInjection(RemoteRsServer.class);
      /* startup */
      bind(RemoteServerStartup.class).asEagerSingleton();
   }

}
