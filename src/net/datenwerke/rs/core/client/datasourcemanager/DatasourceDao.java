package net.datenwerke.rs.core.client.datasourcemanager;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.rpc.DatasourceRpcServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class DatasourceDao extends Dao {

   private final DatasourceRpcServiceAsync rpcService;

   @Inject
   public DatasourceDao(DatasourceRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void getDefaultDatasource(AsyncCallback<DatasourceDefinitionDto> callback) {
      rpcService.getDefaultDatasource(transformDtoCallback(callback));
   }

}
