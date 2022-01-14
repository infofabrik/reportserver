package net.datenwerke.gf.service.lifecycle.events;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class StartupEvent extends DwLoggedEvent {

   public StartupEvent(Object... properties) {
      super(properties);
   }

   @Override
   public String getLoggedAction() {
      return "REPORTSERVER_STARTUP";
   }

}
