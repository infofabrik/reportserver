package net.datenwerke.rs.birt.client.utils.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto;
import net.datenwerke.rs.birt.client.utils.dto.BirtParameterProposalDto;

@RemoteServiceRelativePath("birtutils")
public interface BirtUtilsRpcService extends RemoteService {
	
	public List<BirtParameterProposalDto> proposeParametersFor(BirtReportDto report);

	public BirtReportDto addParametersFor(BirtReportDto report, List<BirtParameterProposalDto> proposalDtos) throws ExpectedException; 

}
