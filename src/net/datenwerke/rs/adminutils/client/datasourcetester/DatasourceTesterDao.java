package net.datenwerke.rs.adminutils.client.datasourcetester;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.adminutils.client.datasourcetester.rpc.DatasourceTesterRPCServiceAsync;
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto;

public class DatasourceTesterDao extends Dao {

   private final DatasourceTesterRPCServiceAsync rpcService;

   @Inject
   public DatasourceTesterDao(DatasourceTesterRPCServiceAsync rpcService) {
      super();
      this.rpcService = rpcService;
   }

   public Request testConnection(DatabaseDatasourceDto databaseDto, AsyncCallback<Boolean> callback) {
      return rpcService.testConnection(databaseDto, transformAndKeepCallback(callback));
   }

}
