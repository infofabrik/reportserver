package net.datenwerke.security.service.eventlogger.events;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class InvalidConfigEvent extends DwLoggedEvent {

   public InvalidConfigEvent(Object... properties) {
      super(properties);
   }

   public InvalidConfigEvent(String configName, String error) {
      super("name", configName, "error", error);
   }

   @Override
   public String getLoggedAction() {
      return "INVALID_CONFIG";
   }

}
