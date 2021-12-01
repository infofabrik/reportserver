package net.datenwerke.rs.adminutils.client.datasourcetester.rpc;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("convenience_datasourcetester")
public interface DatasourceTesterRPCService extends RemoteService {

	public boolean testConnection(DatabaseDatasourceDto databaseDto) throws ServerCallFailedException;
}
