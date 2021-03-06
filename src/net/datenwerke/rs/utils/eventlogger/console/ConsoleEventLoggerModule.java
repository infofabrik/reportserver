package net.datenwerke.rs.utils.eventlogger.console;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import net.datenwerke.rs.utils.eventlogger.EventLoggerEventHandler;
import net.datenwerke.rs.utils.eventlogger.EventLoggerService;

public class ConsoleEventLoggerModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(EventLoggerService.class).to(ConsoleEventLoggerServiceImpl.class).in(Singleton.class);
      bind(EventLoggerEventHandler.class).to(ConsoleEventHandler.class).in(Singleton.class);

      bind(ConsoleEventLoggerStartup.class).asEagerSingleton();
   }

}
