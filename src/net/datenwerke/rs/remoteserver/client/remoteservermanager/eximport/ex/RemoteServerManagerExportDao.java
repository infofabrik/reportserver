package net.datenwerke.rs.remoteserver.client.remoteservermanager.eximport.ex;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.AbstractRemoteServerManagerNodeDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.eximport.ex.rpc.RemoteServerManagerExportRpcServiceAsync;

public class RemoteServerManagerExportDao extends Dao {

   private final RemoteServerManagerExportRpcServiceAsync rpcService;

   @Inject
   public RemoteServerManagerExportDao(RemoteServerManagerExportRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void quickExport(AbstractRemoteServerManagerNodeDto dto, AsyncCallback<Void> callback) {
      rpcService.quickExport(dto, transformAndKeepCallback(callback));
   }

   public void loadResult(AsyncCallback<String> callback) {
      rpcService.loadResult(transformAndKeepCallback(callback));
   }
}