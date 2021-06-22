package net.datenwerke.rs.passwordpolicy.service.events;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class ActivatedUserEvent extends DwLoggedEvent {

   @Override
   public String getLoggedAction() {
      return "ACTIVATED_USER";
   }

}
