package net.datenwerke.rs.crystal.client.crystal.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalParameterProposalDto;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportDto;

@RemoteServiceRelativePath("crystalutils")
public interface CrystalUtilsRpcService extends RemoteService {

   public List<CrystalParameterProposalDto> proposeParametersFor(CrystalReportDto report)
         throws ServerCallFailedException;

   public CrystalReportDto addParametersFor(CrystalReportDto report, List<CrystalParameterProposalDto> proposalDtos)
         throws ServerCallFailedException;

   Boolean hasCrystalLibraries() throws ServerCallFailedException;

}
