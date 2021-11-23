package net.datenwerke.rs.adminutils.client.systemconsole.connpool.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.adminutils.client.systemconsole.connpool.dto.ConnectionPoolDatasourceDto;
import net.datenwerke.rs.adminutils.client.systemconsole.connpool.dto.ConnectionPoolInfoDto;

public interface ConnectionPoolConsoleRpcServiceAsync {   
   void loadDatasources(AsyncCallback<List<ConnectionPoolDatasourceDto>> callback);

   void getDatasourceById(Long id, AsyncCallback<ConnectionPoolInfoDto> callback);
}
