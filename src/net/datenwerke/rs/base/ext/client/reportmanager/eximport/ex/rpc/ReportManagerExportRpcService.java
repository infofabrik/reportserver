package net.datenwerke.rs.base.ext.client.reportmanager.eximport.ex.rpc;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("reportmanager_export")
public interface ReportManagerExportRpcService extends RemoteService {

	public void quickExport(AbstractReportManagerNodeDto dto, boolean includeVariants) throws ServerCallFailedException;
	
	public String loadResult() throws ServerCallFailedException;
}
