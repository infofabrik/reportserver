package net.datenwerke.rs.incubator.server.jasperutils;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.jasperutils.dto.JasperParameterProposalDto;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.util.JasperParameterProposal;
import net.datenwerke.rs.base.service.reportengines.jasper.util.JasperUtilsService;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.reportmanager.ReportParameterService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.incubator.client.jasperutils.rpc.JasperUtilsRpcService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;

/**
 * 
 *
 */
@Singleton
public class JasperUtilsRpcServiceImpl extends SecuredRemoteServiceServlet implements JasperUtilsRpcService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5349153525496058426L;

	private final ReportService reportService;
	private final ReportParameterService parameterService;
	private final DtoService dtoService;
	private final JasperUtilsService jasperUtils;
	
	@Inject
	public JasperUtilsRpcServiceImpl(
		ReportService reportManagerService,
		ReportParameterService parameterService,
		DtoService dtoService,
		JasperUtilsService jasperUtils
		){
		
		this.reportService = reportManagerService;
		this.parameterService = parameterService;
		this.dtoService = dtoService;
		this.jasperUtils = jasperUtils;
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
	public List<JasperParameterProposalDto> proposeParametersFor(
			@Named("report") JasperReportDto jasperReportDto) {
		
		/* load jasper report */
		JasperReport report = (JasperReport) dtoService.loadPoso(jasperReportDto);
		if(null == report || null == report.getMasterFile())
			return null;
		
		List<JasperParameterProposal> proposals = jasperUtils.extractParameters(report.getMasterFile().getContent());
		
		List<JasperParameterProposalDto> proposalDtos = new ArrayList<JasperParameterProposalDto>();
		for(JasperParameterProposal proposal : proposals)
			proposalDtos.add((JasperParameterProposalDto) dtoService.createDto(proposal));
		
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
	public JasperReportDto addParametersFor(@Named("report") JasperReportDto jasperReportDto, List<JasperParameterProposalDto> proposalDtos) throws ExpectedException {
		/* load jasper report */
		JasperReport report = (JasperReport) dtoService.loadPoso(jasperReportDto);
		if(null == report)
			return null;
		
		for(JasperParameterProposalDto proposal : proposalDtos){
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
		
		return (JasperReportDto) dtoService.createDto(report);
	}

}
