package net.datenwerke.security.service.authenticator.events;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class SuEvent extends DwLoggedEvent {

   public SuEvent(Object... properties) {
      super(properties);
   }

   @Override
   public String getLoggedAction() {
      return "SU";
   }

}