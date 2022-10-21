package net.datenwerke.rs.condition.service.condition;

import java.util.List;

import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.condition.client.condition.Condition;
import net.datenwerke.rs.condition.client.condition.dto.SimpleCondition;
import net.datenwerke.rs.condition.service.condition.entity.ReportCondition;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.security.service.usermanager.entities.User;

public interface ConditionService {

   public ReportCondition getReportConditionById(Long id);

   public List<ReportCondition> getReportConditions();

   public void persist(ReportCondition condition);

   public ReportCondition merge(ReportCondition condition);

   public void remove(ReportCondition condition);

   public List<String> getReplacementsFor(ReportCondition condition);

   public boolean executeCondition(Condition condition, String expression) throws ReportExecutorException;

   public boolean executeCondition(Condition condition, String expression, User user) throws ReportExecutorException;

   public List<ReportCondition> getReportConditionsFor(TableReport report);

   List<SimpleCondition> getSimpleConditionsFor(Report report);
}
