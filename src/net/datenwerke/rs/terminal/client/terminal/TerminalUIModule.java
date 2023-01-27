package net.datenwerke.rs.terminal.client.terminal;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class TerminalUIModule extends AbstractGinModule {

   public static final String TERMINAL_ID = "terminal";
   
   @Override
   protected void configure() {
      /* service */
      bind(TerminalUIService.class).to(TerminalUIServiceImpl.class).in(Singleton.class);

      /* startup */
      bind(TerminalUIStartup.class).asEagerSingleton();
   }

}
