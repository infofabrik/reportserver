package net.datenwerke.scheduler.service.scheduler.events;

import net.datenwerke.security.service.eventlogger.jpa.JpaEvent;

public class JobScheduled extends JpaEvent {

   public JobScheduled(Object entity, Object... properties) {
      super(entity, properties);
   }

   @Override
   public String getLoggedAction() {
      return "JOB_SCHEDULED";
   }

}
