package net.datenwerke.rs.adminutils.client.systemconsole.connpool.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.adminutils.client.systemconsole.connpool.dto.ConnectionPoolDatasourceDto;
import net.datenwerke.rs.adminutils.client.systemconsole.connpool.dto.ConnectionPoolInfoDto;

@RemoteServiceRelativePath("connectionpoolconsole")
public interface ConnectionPoolConsoleRpcService extends RemoteService {
   
   List<ConnectionPoolDatasourceDto> loadDatasources() throws ServerCallFailedException;
   
   ConnectionPoolInfoDto getDatasourceById(Long id) throws ServerCallFailedException;
}
