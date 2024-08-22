package net.datenwerke.rs.condition.service.condition.eventhandler;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.condition.service.condition.ConditionService;
import net.datenwerke.rs.condition.service.condition.entity.ReportCondition;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.rs.utils.exception.exceptions.NeedForcefulDeleteException;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;

public class HandleReportRemoveEvent implements EventHandler<RemoveEntityEvent> {

   private final ConditionService conditionService;

   @Inject
   public HandleReportRemoveEvent(ConditionService conditionService) {
      this.conditionService = conditionService;
   }

   @Override
   public void handle(RemoveEntityEvent event) {
      TableReport report = (TableReport) event.getObject();

      List<ReportCondition> condition = conditionService.getReportConditionsFor(report);
      if (null != condition && !condition.isEmpty()) {
         StringBuilder error = new StringBuilder("Report " + report.getId() + " is used as part of a condition.");
         throw new NeedForcefulDeleteException(error.toString());
      }
   }

}