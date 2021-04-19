package net.datenwerke.rs.condition.client.condition;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.condition.client.condition.dto.ReportConditionDto;
import net.datenwerke.rs.condition.client.condition.dto.ScheduleConditionDto;
import net.datenwerke.rs.condition.client.condition.dto.SimpleCondition;
import net.datenwerke.rs.condition.client.condition.rpc.ConditionRpcServiceAsync;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public class ConditionDao extends Dao {

	private final ConditionRpcServiceAsync rpcService;

	@Inject
	public ConditionDao(ConditionRpcServiceAsync rpcService) {
		this.rpcService = rpcService;
	}
	
	public void getConditions(ReportDto report, AsyncCallback<List<Condition>> callback){
		rpcService.getConditions(report, transformAndKeepCallback(callback));
	}

	public void getReplacementsFor(Condition condition,
			AsyncCallback<List<String>> callback){
		if(condition instanceof ReportConditionDto)
			rpcService.getReplacementsFor((ReportConditionDto) condition, transformAndKeepCallback(callback));
		else if(condition instanceof SimpleCondition)
			callback.onSuccess(((SimpleCondition)condition).getReplacements());
	}

	public void executeCondition(ScheduleConditionDto scheduleCondition,
			AsyncCallback<Boolean> callback){
		rpcService.executeCondition(scheduleCondition, transformAndKeepCallback(callback));
	}
}
