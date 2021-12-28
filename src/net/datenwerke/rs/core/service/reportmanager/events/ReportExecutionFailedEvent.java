package net.datenwerke.rs.core.service.reportmanager.events;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class ReportExecutionFailedEvent extends DwLoggedEvent {

   public ReportExecutionFailedEvent(Object... properties) {
      super(properties);
   }

   @Override
   public String getLoggedAction() {
      return "REPORT_EXECUTED_FAILED";
   }

}
