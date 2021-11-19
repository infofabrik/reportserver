package net.datenwerke.rs.core.client.datasinkmanager;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.rpc.DatasinkRpcServiceAsync;

public class DatasinkDao extends Dao {

   private final DatasinkRpcServiceAsync rpcService;

   @Inject
   public DatasinkDao(
         DatasinkRpcServiceAsync rpcService
   ) {
      this.rpcService = rpcService;
   }

   public void getDefaultFolder(DatasinkDefinitionDto datasinkDto, AsyncCallback<String> callback) {
      rpcService.getDefaultFolder(datasinkDto, transformAndKeepCallback(callback));
   }

}
