package net.datenwerke.rs.birt.server;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto;
import net.datenwerke.rs.birt.client.utils.dto.BirtParameterProposalDto;
import net.datenwerke.rs.birt.client.utils.rpc.BirtUtilsRpcService;
import net.datenwerke.rs.birt.service.reportengine.entities.BirtReport;
import net.datenwerke.rs.birt.service.utils.BirtParameterProposal;
import net.datenwerke.rs.birt.service.utils.BirtUtilService;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.reportmanager.ReportParameterService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;

@Singleton
public class BirtUtilsRpcServiceImpl extends SecuredRemoteServiceServlet implements BirtUtilsRpcService {


	private DtoService dtoService;
	private BirtUtilService birtUtils;
	private ReportParameterService parameterService;
	private ReportService reportService;

	@Inject
	public BirtUtilsRpcServiceImpl(DtoService dtoService, BirtUtilService birtUtils, ReportParameterService parameterService, ReportService reportService) {
		this.dtoService = dtoService;
		this.birtUtils = birtUtils;
		this.parameterService = parameterService;
		this.reportService = reportService;
	}

	@SecurityChecked(
			argumentVerification = {
					@ArgumentVerification(
							name = "report",
							isDto = true,
							verify = @RightsVerification(rights={Read.class, Write.class})
							)
			}
			)
	@Override
	@Transactional(rollbackOn={Exception.class})
	public List<BirtParameterProposalDto> proposeParametersFor(@Named("report") BirtReportDto birtReportDto) {

		/* load jasper report */
		BirtReport report = (BirtReport) dtoService.loadPoso(birtReportDto);
		if(null == report || null == report.getReportFile())
			return null;

		List<BirtParameterProposal> proposals = birtUtils.extractParameters(report.getReportFile());

		List<BirtParameterProposalDto> proposalDtos = new ArrayList<BirtParameterProposalDto>();
		for(BirtParameterProposal proposal : proposals)
			proposalDtos.add((BirtParameterProposalDto) dtoService.createDto(proposal));

		return proposalDtos;
	}

	
	
	@SecurityChecked(
			argumentVerification = {
				@ArgumentVerification(
					name = "report",
					isDto = true,
					verify = @RightsVerification(rights={Read.class, Write.class})
				)
			}
		)
	@Override
	@Transactional(rollbackOn={Exception.class})
	public BirtReportDto addParametersFor(@Named("report") BirtReportDto reportDto, List<BirtParameterProposalDto> proposalDtos) throws ExpectedException {
		BirtReport report = (BirtReport) dtoService.loadPoso(reportDto);
		if(null == report)
			return null;

		for(BirtParameterProposalDto proposal : proposalDtos){
			if(null == proposal.getParameterProposal())
				continue;

			ParameterDefinition definition = (ParameterDefinition) dtoService.createPoso(proposal.getParameterProposal());

			/* init default values */
			definition.initWithDefaultValues();

			/* override with parameters from proposal */
			definition.setKey(proposal.getKey());
			definition.setName(proposal.getName());

			/* add to report/folder */
			parameterService.addParameterDefinition(report, definition);
		}

		/* persist parameter */
		reportService.merge(report);

		return (BirtReportDto) dtoService.createDto(report);
	}
}
