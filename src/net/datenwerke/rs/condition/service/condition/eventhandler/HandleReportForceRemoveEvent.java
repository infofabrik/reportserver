package net.datenwerke.rs.condition.service.condition.eventhandler;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.condition.service.condition.ConditionService;
import net.datenwerke.rs.condition.service.condition.entity.ReportCondition;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.security.service.eventlogger.jpa.ForceRemoveEntityEvent;

public class HandleReportForceRemoveEvent implements EventHandler<ForceRemoveEntityEvent> {

   private final ConditionService conditionService;

   @Inject
   public HandleReportForceRemoveEvent(ConditionService conditionService) {
      this.conditionService = conditionService;
   }

   @Override
   public void handle(ForceRemoveEntityEvent event) {
      TableReport report = (TableReport) event.getObject();

      List<ReportCondition> conditions = conditionService.getReportConditionsFor(report);
      if (null != conditions && !conditions.isEmpty()) {
         for (ReportCondition condition : conditions) {
            conditionService.remove(condition);
         }
      }
   }

}