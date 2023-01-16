package net.datenwerke.rs.core.client.datasourcemanager;

import java.util.Map;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.base.client.datasources.DatasourceInfoType;
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.rpc.DatasourceRpcServiceAsync;

public class DatasourceDao extends Dao {

   private final DatasourceRpcServiceAsync rpcService;

   @Inject
   public DatasourceDao(DatasourceRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void getDefaultDatasource(AsyncCallback<DatasourceDefinitionDto> callback) {
      rpcService.getDefaultDatasource(transformDtoCallback(callback));
   }

   public void getDatasourceInfoDetailsAsHtml(DatabaseDatasourceDto datasourceDto,
         AsyncCallback<Map<DatasourceInfoType, SafeHtml>> callback) {
      rpcService.getDatasourceInfoDetailsAsHtml(datasourceDto, transformAndKeepCallback(callback));
   }

}
