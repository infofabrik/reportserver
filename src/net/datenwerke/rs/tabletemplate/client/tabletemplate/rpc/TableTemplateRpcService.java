package net.datenwerke.rs.tabletemplate.client.tabletemplate.rpc;

import java.util.Collection;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateDto;

@RemoteServiceRelativePath("tabletemplate")
public interface TableTemplateRpcService extends RemoteService {

	List<TableReportTemplateDto> loadTemplates(ReportDto report, String executeToken) throws ServerCallFailedException;
	
	void removeTemplates(ReportDto report, String executeToken, Collection<TableReportTemplateDto> templates) throws ServerCallFailedException;

}