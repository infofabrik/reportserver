package net.datenwerke.rs.core.server.parameters;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ViolatedSecurityExceptionDto;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.rpc.ParameterRpcService;
import net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.reportmanager.ReportParameterService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityServiceSecuree;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Delete;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

/**
 * 
 *
 */
@Singleton
public class ParameterRpcServiceImpl extends SecuredRemoteServiceServlet implements ParameterRpcService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2153109628964433163L;

	private final ReportService reportManager;
	private final ReportParameterService parameterService;
	private final DtoService dtoGenerator;
	private final SecurityService securityService;
	private final EntityClonerService entityCloner;
	
	@Inject
	public ParameterRpcServiceImpl(
		EntityClonerService entityCloner,
		ReportService reportManager,
		ReportParameterService parameterService,
		DtoService dtoGenerator,
		SecurityService securityService
	) {
		
		/* store objects */
		this.entityCloner = entityCloner;
		this.reportManager = reportManager;
		this.parameterService = parameterService;
		this.dtoGenerator = dtoGenerator;
		this.securityService = securityService;
	}
	
	@SecurityChecked(
		argumentVerification = {
			@ArgumentVerification(
				name = "node",
				isDto = true,
				verify = @RightsVerification(rights={Read.class, Write.class})
			)
		}
	)
	@Transactional(rollbackOn={Exception.class})
	@Override
	public ReportDto addParameter(ParameterDefinitionDto parameter, @Named("node")AbstractNodeDto correspondingNode) throws ServerCallFailedException {
		/* make sure node is of the correct type */
		if(! (correspondingNode instanceof ReportDto) && ! (correspondingNode instanceof ReportFolderDto))
			throw new IllegalArgumentException("Parameters can only be added to reports or folders. " + correspondingNode.getClass().getName() + " was passed."); //$NON-NLS-1$ //$NON-NLS-2$
		
		/* get node */
		Report realReport = reportManager.getReportById(correspondingNode.getId());
		
		/* get new entity */
		ParameterDefinition definition = (ParameterDefinition) dtoGenerator.instantiatePoso(parameter.getClass());
		
		parameterService.persist(definition);
		
		/* init default values */
		definition.initWithDefaultValues();
		
		/* update key */
		int i = 1;
		while(null != realReport.getParameterDefinitionByKey("key" + i))
			i++;
		
		definition.setKey("key" + i);
		
		/* add to report */
		parameterService.addParameterDefinition(realReport, definition);
		
		/* persist parameter */
		reportManager.merge(realReport);
		
		/* get dto */
		return (ReportDto) dtoGenerator.createDtoFullAccess(realReport);	
	}


	@Transactional(rollbackOn={Exception.class})
	@Override
	public ReportDto updateParameter(ParameterDefinitionDto parameter) throws ServerCallFailedException, ExpectedException {
		/* get Parameter */
		ParameterDefinition realParameter = parameterService.getParameterById(parameter.getId());

		/* get corresponding node */
		AbstractReportManagerNode node = parameterService.getReportWithParameter(realParameter);
		
		/* check if there is already a parameter with this name */
		ParameterDefinition isThereAnybody = parameterService.getParameterByKey(node.getId(), parameter.getKey());
		
		if(isThereAnybody != null && ! isThereAnybody.equals(realParameter))
			throw new ExpectedException("A parameter with this key already exists");
		
		/* validate request */
		if(! securityService.checkRights(node, SecurityServiceSecuree.class, Read.class, Write.class))
			throw new ViolatedSecurityExceptionDto();
		
		/* copy parameters */
		dtoGenerator.mergePoso(parameter, realParameter);
		
		/* merge parameter */
		try{
			parameterService.merge(realParameter);
		} catch(IllegalArgumentException e){
			throw new ExpectedException(e);
		}
		
		/* return dto */
		return (ReportDto) dtoGenerator.createDtoFullAccess(node);
	}
	
	@Transactional(rollbackOn={Exception.class})
	@Override
	public ReportDto updateParameterInstances(Collection<ParameterDefinitionDto> parameterDtos) throws ServerCallFailedException{
		Collection<ParameterDefinition> parameters = getParametersAndCheckRights(parameterDtos);
	
		/* get corresponding node */
		Report report = parameterService.getReportWithParameter(parameters.iterator().next());
		
		for(ParameterDefinition parameter : parameters)
			parameterService.updateParameterInstances(report, parameter);
		
		/* merge report */
		reportManager.merge(report);
		
		return (ReportDto) dtoGenerator.createDtoFullAccess(report);
	}

	
	@Transactional(rollbackOn={Exception.class})
	@Override
	public ReportDto removeParameters(Collection<ParameterDefinitionDto> parameterDtos) throws ServerCallFailedException {
		Collection<ParameterDefinition> parameters = getParametersAndCheckRights(parameterDtos);
		
		/* get corresponding node */
		Report report = parameterService.getReportWithParameter(parameters.iterator().next());
		
		/* remove parameter */
		for(ParameterDefinition parameter : parameters)
			parameterService.remove(parameter);
		
		/* merge report */
		reportManager.merge(report);
		
		return (ReportDto) dtoGenerator.createDtoFullAccess(report);	
	}

	/**
	 * a parameter moved
	 */
	@Transactional(rollbackOn={Exception.class})
	@Override
	public ReportDto movedParameter(ParameterDefinitionDto parameter, int to) throws ServerCallFailedException {
		/* get Parameter */
		ParameterDefinition realParameter = parameterService.getParameterById(parameter.getId());

		/* get corresponding node */
		AbstractReportManagerNode parametersNode = parameterService.getReportWithParameter(realParameter);
		
		// TODO proper exception
		if(! securityService.checkRights(parametersNode, SecurityServiceSecuree.class, Read.class, Write.class))
			throw new ViolatedSecurityExceptionDto();
		
		/* move paramter */
		AbstractReportManagerNode node = parameterService.moveParameter(realParameter, to);

		/* return dto */
		return (ReportDto) dtoGenerator.createDtoFullAccess(node);
	}

	
	private Collection<ParameterDefinition> getParametersAndCheckRights(
			Collection<ParameterDefinitionDto> parameterDtos) throws ViolatedSecurityExceptionDto {
		if(null == parameterDtos || parameterDtos.isEmpty())
			throw new IllegalArgumentException("Submitted list is null or empty."); //$NON-NLS-1$
		
		/* get Parameter */
		Collection<ParameterDefinition> parameters = new HashSet<ParameterDefinition>();
		for(ParameterDefinitionDto paramterDto : parameterDtos){
			ParameterDefinition parameter = parameterService.getParameterById(paramterDto.getId());
			parameters.add(parameter);
		}

		/* get corresponding node */
		Report report = parameterService.getReportWithParameter(parameters.iterator().next());
		
		/* check rights */
		for(ParameterDefinition parameter : parameters){
			AbstractReportManagerNode node = parameterService.getReportWithParameter(parameter);
			if(! report.equals(node))
				throw new IllegalStateException("Parameters have to belong the same node"); //$NON-NLS-1$
		}
		
		if(! securityService.checkRights(report, SecurityServiceSecuree.class, Read.class, Write.class, Delete.class))
			throw new ViolatedSecurityExceptionDto();
		
		return parameters;
	}

	@Override
	@Transactional(rollbackOn={Exception.class})
	public ReportDto duplicateParameters(List<ParameterDefinitionDto> parameterDtos,
			AbstractNodeDto correspondingNode)
			throws ServerCallFailedException, ExpectedException {
		if(null == parameterDtos || parameterDtos.isEmpty())
			return null;
		
		/* get node */
		Report realReport = reportManager.getReportById(correspondingNode.getId());
		
		for(ParameterDefinitionDto parameterDto : parameterDtos){
			ParameterDefinition definition = (ParameterDefinition) dtoGenerator.loadPoso(parameterDto);
			definition = entityCloner.cloneEntity(definition);
			
			if(null == definition)
				throw new ExpectedException("Parameter has been deleted");
			
			definition.cleanDuplicated();
			parameterService.persist(definition);
			
			/* update key */
			String oldKey = null == definition.getKey() ? "key" : definition.getKey();
			if(null != realReport.getParameterDefinitionByKey(oldKey)){
				int i = 1;
				oldKey = null == definition.getKey() ? "key" : definition.getKey();
				while(null != realReport.getParameterDefinitionByKey(oldKey + i))
					i++;
				definition.setKey(oldKey + i);
			}
			
			/* add to report */
			parameterService.addParameterDefinition(realReport, definition);
		}
		
		/* persist parameter */
		reportManager.merge(realReport);
		
		/* get dto */
		return (ReportDto) dtoGenerator.createDtoFullAccess(realReport);
	}


}
