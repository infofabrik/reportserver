package net.datenwerke.rs.base.client.parameters.datasource.rpc;

import java.util.Collection;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.ListLoadResult;

import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public interface DatasourceParameterRPCServiceAsync {

	void loadDatasourceParameterData(DatasourceParameterDefinitionDto queryParameterDTO,  Collection<ParameterInstanceDto> dependsOnParameterDTOs,
			boolean mergeDefinition, ReportDto reportDto, AsyncCallback<ListLoadResult<DatasourceParameterDataDto>> callback);

	void allowDatasourceParameterPostProcessing(AsyncCallback<Boolean> callback);

}
