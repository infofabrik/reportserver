package net.datenwerke.rs.base.ext.client.dashboardmanager.eximport.ex.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.dashboard.client.dashboard.dto.AbstractDashboardManagerNodeDto;

@RemoteServiceRelativePath("dashboardmanager_export")
public interface DashboardManagerExportRpcService extends RemoteService {

	public void quickExport(AbstractDashboardManagerNodeDto dto) throws ServerCallFailedException;

	public String loadResult() throws ServerCallFailedException;
}
