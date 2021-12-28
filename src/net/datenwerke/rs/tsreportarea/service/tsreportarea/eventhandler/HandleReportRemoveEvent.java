package net.datenwerke.rs.tsreportarea.service.tsreportarea.eventhandler;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskReportReference;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.rs.utils.exception.exceptions.NeedForcefulDeleteException;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;

public class HandleReportRemoveEvent implements EventHandler<RemoveEntityEvent> {

   private final TsDiskService diskService;

   @Inject
   public HandleReportRemoveEvent(TsDiskService diskService) {
      this.diskService = diskService;
   }

   @Override
   public void handle(RemoveEntityEvent event) {
      Report report = (Report) event.getObject();

      List<TsDiskReportReference> references = diskService.getReferencesTo(report);
      if (null != references && !references.isEmpty()) {
         StringBuilder error = new StringBuilder("Report " + report.getId() + " is still referenced in TeamSpaces.");
         throw new NeedForcefulDeleteException(error.toString());
      }
   }

}
