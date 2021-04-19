package net.datenwerke.rs.core.client.datasourcemanager.rpc;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("datasources")
public interface DatasourceRpcService extends RemoteService {
	public DatasourceDefinitionDto getDefaultDatasource()  throws ServerCallFailedException;
	
}
