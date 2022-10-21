package net.datenwerke.rs.incubator.client.jasperutils;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.base.client.jasperutils.dto.JasperParameterProposalDto;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;
import net.datenwerke.rs.incubator.client.jasperutils.rpc.JasperUtilsRpcServiceAsync;

public class JasperUtilsDao extends Dao {

   private final JasperUtilsRpcServiceAsync rpcService;

   @Inject
   public JasperUtilsDao(JasperUtilsRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void proposeParametersFor(JasperReportDto jasperReportDto,
         AsyncCallback<List<JasperParameterProposalDto>> callback) {
      rpcService.proposeParametersFor(jasperReportDto, transformAndKeepCallback(callback));
   }

   public void addParametersFor(JasperReportDto jasperReportDto, List<JasperParameterProposalDto> proposalDtos,
         AsyncCallback<JasperReportDto> callback) {
      rpcService.addParametersFor(jasperReportDto, (List<JasperParameterProposalDto>) unproxy(proposalDtos),
            transformDtoCallback(callback));
   }
}
