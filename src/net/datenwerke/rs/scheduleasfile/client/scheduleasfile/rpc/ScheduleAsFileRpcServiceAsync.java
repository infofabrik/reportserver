package net.datenwerke.rs.scheduleasfile.client.scheduleasfile.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;


public interface ScheduleAsFileRpcServiceAsync  {

	void exportIntoTeamSpace(ReportDto reportDto, String executorToke,
			String format, List<ReportExecutionConfigDto> configs,
			AbstractTsDiskNodeDto folder, String name, String description,
			AsyncCallback<Void> callback);
	
}
