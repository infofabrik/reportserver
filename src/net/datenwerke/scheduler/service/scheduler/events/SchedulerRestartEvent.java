package net.datenwerke.scheduler.service.scheduler.events;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class SchedulerRestartEvent extends DwLoggedEvent {

   public SchedulerRestartEvent(Object... properties) {
      super(properties);
   }

   @Override
   public String getLoggedAction() {
      return "SCHEDULER_RESTART";
   }

}
