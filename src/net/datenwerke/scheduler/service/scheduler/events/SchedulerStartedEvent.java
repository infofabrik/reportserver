package net.datenwerke.scheduler.service.scheduler.events;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class SchedulerStartedEvent extends DwLoggedEvent {

   public SchedulerStartedEvent(Object... properties) {
      super(properties);
   }

   @Override
   public String getLoggedAction() {
      return "SCHEDULER_STARTED";
   }

}
