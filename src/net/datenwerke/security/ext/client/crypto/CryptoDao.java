package net.datenwerke.security.ext.client.crypto;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.security.ext.client.crypto.rpc.CryptoRpcServiceAsync;

public class CryptoDao extends Dao {

   private final CryptoRpcServiceAsync rpcService;

   @Inject
   public CryptoDao(CryptoRpcServiceAsync rpcService) {
      super();
      this.rpcService = rpcService;
   }

   public void getHmacPassphrase(AsyncCallback<String> callback) {
      rpcService.getHmacPassphrase(transformAndKeepCallback(callback));
   }

   public void getSalt(AsyncCallback<String> callback) {
      rpcService.getSalt(transformAndKeepCallback(callback));
   }

   public void getKeyLength(AsyncCallback<Integer> callback) {
      rpcService.getKeyLength(transformAndKeepCallback(callback));
   }

   public void getUserSalt(String username, AsyncCallback<String> callback) {
      rpcService.getUserSalt(username, transformAndKeepCallback(callback));
   }

   public void getUserSalt(AsyncCallback<String> callback) {
      rpcService.getUserSalt(transformAndKeepCallback(callback));
   }
}
