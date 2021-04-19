package net.datenwerke.rs.incubator.client.jasperutils.rpc;

import java.util.List;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.base.client.jasperutils.dto.JasperParameterProposalDto;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("jasperutils")
public interface JasperUtilsRpcService extends RemoteService{

	public List<JasperParameterProposalDto> proposeParametersFor(JasperReportDto jasperReportDto) throws ServerCallFailedException;
	
	public JasperReportDto addParametersFor(JasperReportDto jasperReportDto, List<JasperParameterProposalDto> proposalDtos)  throws ServerCallFailedException;
}
