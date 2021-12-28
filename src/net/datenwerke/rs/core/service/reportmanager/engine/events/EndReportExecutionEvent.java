package net.datenwerke.rs.core.service.reportmanager.engine.events;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class EndReportExecutionEvent extends DwLoggedEvent {

   public EndReportExecutionEvent(Report report, String uuid, boolean success) {
      super("report_id", null != report.getId() ? report.getId() : report.getOldTransientId(), "uuid", uuid, "success",
            success);
   }

   @Override
   public String getLoggedAction() {
      return "END_REPORT_EXECUTION";
   }

}
