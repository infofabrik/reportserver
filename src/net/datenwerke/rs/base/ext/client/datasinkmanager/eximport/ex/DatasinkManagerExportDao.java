package net.datenwerke.rs.base.ext.client.datasinkmanager.eximport.ex;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.base.ext.client.datasinkmanager.eximport.ex.rpc.DatasinkManagerExportRpcServiceAsync;
import net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto;

public class DatasinkManagerExportDao extends Dao {

   private final DatasinkManagerExportRpcServiceAsync rpcService;

   @Inject
   public DatasinkManagerExportDao(DatasinkManagerExportRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void quickExport(AbstractDatasinkManagerNodeDto dto, AsyncCallback<Void> callback) {
      rpcService.quickExport(dto, transformAndKeepCallback(callback));
   }

   public void loadResult(AsyncCallback<String> callback) {
      rpcService.loadResult(transformAndKeepCallback(callback));
   }
}