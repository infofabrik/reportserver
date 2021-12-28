package net.datenwerke.rs.base.client.parameters.datasource.rpc;

import java.util.Collection;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.ListLoadResult;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

@RemoteServiceRelativePath("rs_basicparameters_datasource")
public interface DatasourceParameterRPCService extends RemoteService {

   public ListLoadResult<DatasourceParameterDataDto> loadDatasourceParameterData(
         DatasourceParameterDefinitionDto queryParameterDTO, Collection<ParameterInstanceDto> dependsOnParameterDTOs,
         boolean mergeDefinition, ReportDto reportDto) throws ServerCallFailedException;

   public boolean allowDatasourceParameterPostProcessing();
}
