package net.datenwerke.rs.incubator.client.jaspertotable.rpc;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;
import net.datenwerke.rs.incubator.client.jaspertotable.dto.JasperToTableConfigDto;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("datenwerke/jaspertotable")
public interface JasperToTableRpcService extends RemoteService {

	public JasperToTableConfigDto getConfig(JasperReportDto report) throws ServerCallFailedException;
	public void setConfig(JasperReportDto report, JasperToTableConfigDto config) throws ServerCallFailedException;
}
