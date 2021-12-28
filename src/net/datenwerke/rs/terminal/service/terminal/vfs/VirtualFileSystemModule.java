package net.datenwerke.rs.terminal.service.terminal.vfs;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;
import net.datenwerke.rs.terminal.service.terminal.vfs.hooks.TreeBasedVirtualFileSystem;

public class VirtualFileSystemModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
      /* startup */
      bind(VirtualFileSystemStartup.class).asEagerSingleton();

      /* static injection */
      requestStaticInjection(TreeBasedVirtualFileSystem.class);
   }

}
