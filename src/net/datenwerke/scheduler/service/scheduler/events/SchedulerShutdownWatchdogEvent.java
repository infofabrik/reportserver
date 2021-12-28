package net.datenwerke.scheduler.service.scheduler.events;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class SchedulerShutdownWatchdogEvent extends DwLoggedEvent {

   public SchedulerShutdownWatchdogEvent(Object... properties) {
      super(properties);
   }

   @Override
   public String getLoggedAction() {
      return "SCHEDULER_SHUTDOWN_WATCHDOG";
   }

}
