package net.datenwerke.rs.crystal.client.crystal.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.crystal.client.crystal.dto.CrystalParameterProposalDto;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportDto;

public interface CrystalUtilsRpcServiceAsync {

	void proposeParametersFor(CrystalReportDto report, AsyncCallback<List<CrystalParameterProposalDto>> callback);

	void addParametersFor(CrystalReportDto report, List<CrystalParameterProposalDto> proposalDtos, AsyncCallback<CrystalReportDto> callback);

	void hasCrystalLibraries(AsyncCallback<Boolean> callback);


}
