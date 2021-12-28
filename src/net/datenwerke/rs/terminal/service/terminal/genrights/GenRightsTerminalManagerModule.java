package net.datenwerke.rs.terminal.service.terminal.genrights;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class GenRightsTerminalManagerModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
      bind(GenRightsTerminalManagerStartup.class).asEagerSingleton();
   }

}
