package net.datenwerke.scheduler.service.scheduler.events;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class SchedulerDaemonRessurectionEvent extends DwLoggedEvent {

   public SchedulerDaemonRessurectionEvent(Object... properties) {
      super(properties);
   }

   @Override
   public String getLoggedAction() {
      return "SCHEDULER_DAEMON_RESSURECTION_ATTEMPT";
   }

}
