package net.datenwerke.rs.saiku.client.saiku;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.ListLoadResult;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto;
import net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto;

@RemoteServiceRelativePath("saiku")
public interface SaikuRpcService extends RemoteService {
	
	public void stashReport(String token, SaikuReportDto report) throws ServerCallFailedException;

	ListLoadResult<String> loadCubesFor(MondrianDatasourceDto datasourceDefinitionDto, SaikuReportDto saikuReportDto) throws ServerCallFailedException;

	public void clearCache(MondrianDatasourceDto datasourceDefinitionDto) throws ServerCallFailedException;
}
