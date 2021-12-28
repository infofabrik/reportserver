package net.datenwerke.gf.service.maintenance.event;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class MaintenanceJobFailed extends DwLoggedEvent {

   public MaintenanceJobFailed(Object... properties) {
      super(properties);
   }

   @Override
   public String getLoggedAction() {
      return "MAINTENANCE_JOB_FAILED";
   }

}
