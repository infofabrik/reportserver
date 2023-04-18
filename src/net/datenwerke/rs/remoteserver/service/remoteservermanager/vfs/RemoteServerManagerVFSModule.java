package net.datenwerke.rs.remoteserver.service.remoteservermanager.vfs;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class RemoteServerManagerVFSModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
      bind(RemoteServerManagerVFSStartup.class).asEagerSingleton();
   }

}
