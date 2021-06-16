package net.datenwerke.rs.terminal.service.terminal;

import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.ServletScopes;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;
import net.datenwerke.rs.terminal.service.terminal.genrights.GenRightsTerminalManagerModule;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.HelpMessageInterceptor;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.objresolver.ObjectResolverModule;
import net.datenwerke.rs.terminal.service.terminal.vfs.VirtualFileSystemModule;

/**
 * 
 *
 */
public class TerminalModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
      bind(TerminalService.class).to(TerminalServiceImpl.class).in(Singleton.class);
      bind(TerminalSessionMap.class).in(ServletScopes.SESSION);

      /* submodules */
      install(new VirtualFileSystemModule());
      install(new ObjectResolverModule());

      /* interceptors */
      HelpMessageInterceptor helpMessageInterceptor = new HelpMessageInterceptor();
      requestInjection(helpMessageInterceptor);
      bindInterceptor(Matchers.any(), Matchers.annotatedWith(CliHelpMessage.class), helpMessageInterceptor);

      /* startup */
      bind(TerminalStartup.class).asEagerSingleton();

      /* rights */
      install(new GenRightsTerminalManagerModule());
   }

}
