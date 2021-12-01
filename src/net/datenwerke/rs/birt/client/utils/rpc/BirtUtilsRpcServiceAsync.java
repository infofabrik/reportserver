package net.datenwerke.rs.birt.client.utils.rpc;

import java.util.List;

import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto;
import net.datenwerke.rs.birt.client.utils.dto.BirtParameterProposalDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BirtUtilsRpcServiceAsync {

	void proposeParametersFor(BirtReportDto report, AsyncCallback<List<BirtParameterProposalDto>> callback);
	
	void addParametersFor(BirtReportDto report, List<BirtParameterProposalDto> proposalDtos, AsyncCallback<BirtReportDto> rsAsyncCallback);

}
