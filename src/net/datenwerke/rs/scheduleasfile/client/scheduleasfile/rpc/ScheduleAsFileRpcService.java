package net.datenwerke.rs.scheduleasfile.client.scheduleasfile.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;

@RemoteServiceRelativePath("ts/scheduelasfile")
public interface ScheduleAsFileRpcService extends RemoteService {

	void exportIntoTeamSpace(ReportDto reportDto, String executorToke,
			String format, List<ReportExecutionConfigDto> configs,
			AbstractTsDiskNodeDto folder, String name, String description) throws ServerCallFailedException;
	
}
