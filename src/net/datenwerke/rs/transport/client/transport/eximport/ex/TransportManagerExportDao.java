package net.datenwerke.rs.transport.client.transport.eximport.ex;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.transport.client.transport.dto.AbstractTransportManagerNodeDto;
import net.datenwerke.rs.transport.client.transport.eximport.ex.rpc.TransportManagerExportRpcServiceAsync;

public class TransportManagerExportDao extends Dao {

   private final TransportManagerExportRpcServiceAsync rpcService;

   @Inject
   public TransportManagerExportDao(TransportManagerExportRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void quickExport(AbstractTransportManagerNodeDto dto, AsyncCallback<Void> callback) {
      rpcService.quickExport(dto, transformAndKeepCallback(callback));
   }

   public void loadResult(AsyncCallback<String> callback) {
      rpcService.loadResult(transformAndKeepCallback(callback));
   }
}