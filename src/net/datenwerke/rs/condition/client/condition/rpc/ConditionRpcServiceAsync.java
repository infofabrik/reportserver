package net.datenwerke.rs.condition.client.condition.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.condition.client.condition.Condition;
import net.datenwerke.rs.condition.client.condition.dto.ReportConditionDto;
import net.datenwerke.rs.condition.client.condition.dto.ScheduleConditionDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public interface ConditionRpcServiceAsync {

   void getConditions(ReportDto report, AsyncCallback<List<Condition>> callback);

   void getReplacementsFor(ReportConditionDto condition, AsyncCallback<List<String>> callback);

   void executeCondition(ScheduleConditionDto scheduleCondition, AsyncCallback<Boolean> callback);

}
