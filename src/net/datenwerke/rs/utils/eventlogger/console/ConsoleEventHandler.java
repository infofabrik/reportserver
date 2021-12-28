package net.datenwerke.rs.utils.eventlogger.console;

import net.datenwerke.rs.utils.eventlogger.EventLoggerEventHandler;
import net.datenwerke.rs.utils.eventlogger.eventbus.LoggedEvent;

public class ConsoleEventHandler implements EventLoggerEventHandler {

   @Override
   public void handle(LoggedEvent event) {
      System.out.println(event.getLoggedAction());
   }

}
