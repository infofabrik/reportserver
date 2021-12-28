package net.datenwerke.security.service.authenticator.events;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class LoginEvent extends DwLoggedEvent {

   public LoginEvent(Object... properties) {
      super(properties);
   }

   @Override
   public String getLoggedAction() {
      return "LOGIN";
   }

}