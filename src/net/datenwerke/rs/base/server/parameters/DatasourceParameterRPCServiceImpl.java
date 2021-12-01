package net.datenwerke.rs.base.server.parameters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoadResultBean;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ViolatedSecurityExceptionDto;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDefinitionDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.decorator.DatasourceParameterDataDtoDec;
import net.datenwerke.rs.base.client.parameters.datasource.rpc.DatasourceParameterRPCService;
import net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterData;
import net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterDefinition;
import net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterService;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.reportmanager.ReportParameterService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.hooks.ParameterInstanceCreatedFromDtoHook;
import net.datenwerke.rs.core.service.reportmanager.locale.ReportManagerMessages;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSetFactory;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityServiceSecuree;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;

/**
 * 
 *
 */
@Singleton
public class DatasourceParameterRPCServiceImpl extends SecuredRemoteServiceServlet
		implements DatasourceParameterRPCService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1998224910408470653L;

	private final ReportParameterService parameterService;
	private final DatasourceParameterService datasourceParameterService;
	private final DtoService dtoService;
	private final HookHandlerService hookHandler;
	private final SecurityService securityService;
	private final ParameterSetFactory parameterSetFactory;

	
	
	
	@Inject
	public DatasourceParameterRPCServiceImpl(
		ReportParameterService parameterService,
		DatasourceParameterService datasourceParameterService,
		DtoService dtoService,
		SecurityService securityService,
		HookHandlerService hookHandler,
		ParameterSetFactory parameterSetFactory
		){
		
		/* store objects */
		this.parameterService = parameterService;
		this.datasourceParameterService = datasourceParameterService;
		this.dtoService = dtoService;
		this.securityService = securityService;
		this.hookHandler = hookHandler;
		this.parameterSetFactory = parameterSetFactory;
	}
	
	/**
	 * if dependsOnParameterDTOs is null we'll use the depend's on stored in the databse.
	 */
	@Override
	@Transactional(rollbackOn={Exception.class})
	public ListLoadResult<DatasourceParameterDataDto> loadDatasourceParameterData (
			DatasourceParameterDefinitionDto parameterDto, 
			Collection<ParameterInstanceDto> dependsOnParameterDTOs,
			boolean mergeDefinition, ReportDto reportDto) throws ServerCallFailedException {
		/* get Parameter */
		ParameterDefinition realParameter = parameterService.getParameterById(parameterDto.getId());
		
		Report report = null;
		if (null == reportDto) {
		   /* Sometimes reportDto is not available e.g. when it is not selected in the tree, as there is no tree available as in the
		    * dashboards.
		    */
           report = parameterService.getReportWithParameter(realParameter);
		} else
		   report = (Report) dtoService.loadPoso(reportDto);
		
		securityService.assertRights(report, Read.class, Execute.class);

		/* get real object */
		ParameterDefinition<?> unmanagedParameter = parameterService.getUnmanagedParameterById(parameterDto.getId());
		
		if(mergeDefinition){
			if(! securityService.checkRights(report, SecurityServiceSecuree.class, Write.class))
				throw new ViolatedSecurityExceptionDto();
				
			dtoService.mergeUnmanagedPoso(parameterDto, unmanagedParameter);
		}

		/* validate recieved parameter */
		if(! validateParameter(unmanagedParameter, parameterDto))
			throw new RuntimeException(new ReportExecutorException(ReportManagerMessages.INSTANCE.exceptionInvalidParameter()));
		
		/* is the parameter of the right type */
		if(! (unmanagedParameter instanceof DatasourceParameterDefinition))
			throw new IllegalArgumentException("Expected datasource parameter"); //$NON-NLS-1$
		
		try {
			List<DatasourceParameterData> parameterData = null;
			if (null == dependsOnParameterDTOs || dependsOnParameterDTOs.isEmpty()) {
				parameterData = datasourceParameterService.getParameterData((DatasourceParameterDefinition) unmanagedParameter, report);
			} else {
				/* create parameter set */
				ParameterSet parameterSet = parameterSetFactory.create();
				
				for(ParameterInstanceDto dependsOnParameterDTO : dependsOnParameterDTOs){
					/* create instance and tell it it is not default any longer */
					ParameterInstance dependsOnParameter = (ParameterInstance) dtoService.createUnmanagedPoso(dependsOnParameterDTO);
					dependsOnParameter.setStillDefault(false);
					
					/* set correct definition */
					if (null != dependsOnParameterDTO.getDtoId()) {
						/* In inline report execution: no stored instance. */
						ParameterInstance storedInstance = (ParameterInstance) dtoService.loadPoso(dependsOnParameterDTO);
						dependsOnParameter.setDefinition(parameterService.getUnmanagedParameter(storedInstance.getDefinition()));
					}
					
					/* postprocess parameter instances */
					for(ParameterInstanceCreatedFromDtoHook hook : hookHandler.getHookers(ParameterInstanceCreatedFromDtoHook.class)){
						if(hook.consumes(dependsOnParameterDTO)){
							hook.posoCreated(dependsOnParameterDTO, dependsOnParameter);
						}
					}
					
					parameterSet.add(dependsOnParameter);
				}
				
				parameterData = datasourceParameterService.getParameterData((DatasourceParameterDefinition) unmanagedParameter,parameterSet);
			}
			
			/* prepare list */
			List<DatasourceParameterDataDto> dataList = new ArrayList<DatasourceParameterDataDto>();

			for(DatasourceParameterData data : parameterData){
				DatasourceParameterDataDto dataObject = new DatasourceParameterDataDtoDec();
				
				dataObject.setValue(data.getValue());
				dataObject.setKey(data.getKey().replace('\r', ' ').replace('\n',' '));
				dataList.add(dataObject);
			}
			
			return new ListLoadResultBean<DatasourceParameterDataDto>(dataList);
		}  catch (ReportExecutorException e){
			throw new ServerCallFailedException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 		
	}
	
	
	private boolean validateParameter(ParameterDefinition referenceDefinition, DatasourceParameterDefinitionDto recievedDefinition) {
		return true;
	}

	@Override
	public boolean allowDatasourceParameterPostProcessing() {
		return datasourceParameterService.isAllowDatasourceParameterPostProcessing();
	}

}
