package net.datenwerke.rs.passwordpolicy.service.events;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;
import net.datenwerke.security.service.usermanager.entities.User;

public class UserLostPasswordEvent extends DwLoggedEvent {

   public UserLostPasswordEvent(User user) {
      super();
      userId = user.getId();
   }

   @Override
   public String getLoggedAction() {
      return "USER_LOST_PASSWORD";
   }

}
