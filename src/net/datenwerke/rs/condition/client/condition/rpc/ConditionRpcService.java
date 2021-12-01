package net.datenwerke.rs.condition.client.condition.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.condition.client.condition.Condition;
import net.datenwerke.rs.condition.client.condition.dto.ReportConditionDto;
import net.datenwerke.rs.condition.client.condition.dto.ScheduleConditionDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

@RemoteServiceRelativePath("conditions")
public interface ConditionRpcService extends RemoteService{

	List<Condition> getConditions(ReportDto report) throws ServerCallFailedException, ExpectedException;
	
	List<String> getReplacementsFor(ReportConditionDto condition) throws ServerCallFailedException, ExpectedException;
	
	boolean executeCondition(ScheduleConditionDto scheduleCondition) throws ServerCallFailedException, ExpectedException;
}