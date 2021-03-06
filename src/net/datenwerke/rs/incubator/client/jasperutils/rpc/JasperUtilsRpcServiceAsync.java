package net.datenwerke.rs.incubator.client.jasperutils.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.base.client.jasperutils.dto.JasperParameterProposalDto;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;

public interface JasperUtilsRpcServiceAsync {

   void proposeParametersFor(JasperReportDto jasperReportDto, AsyncCallback<List<JasperParameterProposalDto>> callback);

   void addParametersFor(JasperReportDto jasperReportDto, List<JasperParameterProposalDto> proposalDtos,
         AsyncCallback<JasperReportDto> callback);

}
