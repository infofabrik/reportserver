package net.datenwerke.rs.crystal.client.crystal;

import java.util.List;

import javax.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalParameterProposalDto;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportDto;
import net.datenwerke.rs.crystal.client.crystal.rpc.CrystalUtilsRpcServiceAsync;

public class CrystalUtilsDao extends Dao {

   private final CrystalUtilsRpcServiceAsync rpcService;

   @Inject
   public CrystalUtilsDao(CrystalUtilsRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void proposeParametersFor(CrystalReportDto report,
         RsAsyncCallback<List<CrystalParameterProposalDto>> callback) {
      rpcService.proposeParametersFor(report, transformAndKeepCallback(callback));
   }

   public void addParametersFor(CrystalReportDto report, List<CrystalParameterProposalDto> proposalDtos,
         RsAsyncCallback<CrystalReportDto> rsAsyncCallback) {
      rpcService.addParametersFor(report, (List<CrystalParameterProposalDto>) unproxy(proposalDtos),
            transformDtoCallback(rsAsyncCallback));
   }

   public void hasCrystalLibraries(RsAsyncCallback<Boolean> callback) {
      rpcService.hasCrystalLibraries(transformAndKeepCallback(callback));
   }

}
