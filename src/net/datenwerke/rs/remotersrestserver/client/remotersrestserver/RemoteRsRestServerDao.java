package net.datenwerke.rs.remotersrestserver.client.remotersrestserver;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.amazons3.client.amazons3.rpc.AmazonS3RpcServiceAsync;
import net.datenwerke.rs.remotersrestserver.client.remotersrestserver.dto.RemoteRsRestServerDto;
import net.datenwerke.rs.remotersrestserver.client.remotersrestserver.rpc.RemoteRsRestServerRpcServiceAsync;

public class RemoteRsRestServerDao extends Dao {

   private final RemoteRsRestServerRpcServiceAsync rpcService;

   @Inject
   public RemoteRsRestServerDao(RemoteRsRestServerRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public Request test(RemoteRsRestServerDto remoteServer, AsyncCallback<Boolean> callback) {
      return rpcService.test(remoteServer, transformAndKeepCallback(callback));
   }

}
