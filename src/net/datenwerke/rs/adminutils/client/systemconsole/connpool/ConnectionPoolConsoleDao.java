package net.datenwerke.rs.adminutils.client.systemconsole.connpool;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.adminutils.client.systemconsole.connpool.dto.ConnectionPoolDatasourceDto;
import net.datenwerke.rs.adminutils.client.systemconsole.connpool.dto.ConnectionPoolInfoDto;
import net.datenwerke.rs.adminutils.client.systemconsole.connpool.rpc.ConnectionPoolConsoleRpcServiceAsync;

public class ConnectionPoolConsoleDao extends Dao {
   private final ConnectionPoolConsoleRpcServiceAsync rpcService;

   @Inject
   public ConnectionPoolConsoleDao(ConnectionPoolConsoleRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void loadDatasources(RsAsyncCallback<List<ConnectionPoolDatasourceDto>> callback) {
      rpcService.loadDatasources(transformAndKeepCallback(callback));
   }
   
   public void getDatasourceById(long id, RsAsyncCallback<ConnectionPoolInfoDto> callback) {
      rpcService.getDatasourceById(id, transformAndKeepCallback(callback));
   }

}
