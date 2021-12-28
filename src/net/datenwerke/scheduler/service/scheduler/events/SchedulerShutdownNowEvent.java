package net.datenwerke.scheduler.service.scheduler.events;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class SchedulerShutdownNowEvent extends DwLoggedEvent {

   public SchedulerShutdownNowEvent(Object... properties) {
      super(properties);
   }

   @Override
   public String getLoggedAction() {
      return "SCHEDULER_SHUTDOWN_NOW";
   }

}
