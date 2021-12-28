package net.datenwerke.rs.crystal.server.crystal;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalParameterProposalDto;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportDto;
import net.datenwerke.rs.crystal.client.crystal.rpc.CrystalUtilsRpcService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;

@Singleton
public class CrystalUtilsRpcDummyServiceImpl extends SecuredRemoteServiceServlet implements CrystalUtilsRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = -1872709659662933367L;

   @Inject
   public CrystalUtilsRpcDummyServiceImpl() {
   }

   @Override
   public List<CrystalParameterProposalDto> proposeParametersFor(CrystalReportDto report)
         throws ServerCallFailedException {
      return null;
   }

   @Override
   public CrystalReportDto addParametersFor(CrystalReportDto report, List<CrystalParameterProposalDto> proposalDtos)
         throws ServerCallFailedException {
      return null;
   }

   @Override
   public Boolean hasCrystalLibraries() throws ServerCallFailedException {
      return false;
   }

}
