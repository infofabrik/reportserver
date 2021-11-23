package net.datenwerke.rs.adminutils.server.systemconsole.connpool;

import java.util.List;

import com.google.inject.Singleton;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.adminutils.client.systemconsole.connpool.dto.ConnectionPoolDatasourceDto;
import net.datenwerke.rs.adminutils.client.systemconsole.connpool.dto.ConnectionPoolInfoDto;
import net.datenwerke.rs.adminutils.client.systemconsole.connpool.rpc.ConnectionPoolConsoleRpcService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;

@Singleton
public class ConnectionPoolConsoleDummyRpcServiceImpl extends SecuredRemoteServiceServlet
      implements ConnectionPoolConsoleRpcService {

   private static final long serialVersionUID = 1L;

   @Override
   public List<ConnectionPoolDatasourceDto> loadDatasources() {
      return null;
   }

   @Override
   public ConnectionPoolInfoDto getDatasourceById(Long id) throws ServerCallFailedException {
      return null;
   }

}
