package net.datenwerke.rs.base.client.parameters.datasource;

import java.util.Collection;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.sencha.gxt.data.shared.loader.ListLoadResult;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.DaoAsyncCallback;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDefinitionDto;
import net.datenwerke.rs.base.client.parameters.datasource.rpc.DatasourceParameterRPCServiceAsync;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceUIService;
import net.datenwerke.rs.core.client.datasourcemanager.config.DatasourceDefinitionConfigConfigurator;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public class DatasourceParameterDao extends Dao {

	private final DatasourceParameterRPCServiceAsync rpcService;
	private DatasourceUIService dsService;
	private final DatasourceParameterUiService dsParamService;

	@Inject
	public DatasourceParameterDao(
		DatasourceParameterRPCServiceAsync rpcService,
		DatasourceUIService dsService,
		DatasourceParameterUiService dsParamService) {
		super();
		this.rpcService = rpcService;
		this.dsService = dsService;
		this.dsParamService = dsParamService;
	}
	
	public void loadDatasourceParameterData(DatasourceParameterDefinitionDto parameterDefinition,  Collection<ParameterInstanceDto> dependsOnParameterDTOs,
			boolean mergeDefinition, ReportDto reportDto, AsyncCallback<ListLoadResult<DatasourceParameterDataDto>> callback){
		
		/* if no datasource is set and postpressing is disabled or empty */
		if((null == parameterDefinition.getDatasourceContainer() || null == parameterDefinition.getDatasourceContainer().getDatasource()) &&
				(! dsParamService.isAllowPostProcessing() || null == parameterDefinition.getPostProcess() || "".equals(parameterDefinition.getPostProcess().trim()))) {
			callback.onFailure(new ExpectedException(RsMessages.INSTANCE.datasourceNotConfigured()));
			return;
		}
		
		if(null != parameterDefinition.getDatasourceContainer() && null != parameterDefinition.getDatasourceContainer().getDatasource()){
			DatasourceDefinitionConfigConfigurator configurator = dsService.getConfigurator(parameterDefinition.getDatasourceContainer().getDatasource().getClass());
			if(! configurator.isValid(parameterDefinition.getDatasourceContainer())){
				callback.onFailure(new ExpectedException(RsMessages.INSTANCE.datasourceNotConfigured()));
				return;
			}
		}
		
		if(mergeDefinition)
			parameterDefinition = unproxy(parameterDefinition);

		Collection<ParameterInstanceDto> unproxiedParameters = unproxy(dependsOnParameterDTOs);
		DaoAsyncCallback<ListLoadResult<DatasourceParameterDataDto>> transformedCallback = transformAndKeepCallback(callback);
		transformedCallback.setIgnoreAllExceptions(true);
		
		rpcService.loadDatasourceParameterData(parameterDefinition, unproxiedParameters, mergeDefinition, reportDto, transformedCallback);
	}

	public void allowDatasourceParameterPostProcessing(AsyncCallback<Boolean> callback){
		rpcService.allowDatasourceParameterPostProcessing(transformAndKeepCallback(callback));
	}
}
