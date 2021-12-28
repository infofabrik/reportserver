package net.datenwerke.security.ext.client.crypto.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CryptoRpcServiceAsync {

   void getHmacPassphrase(AsyncCallback<String> callback);

   void getSalt(AsyncCallback<String> callback);

   void getKeyLength(AsyncCallback<Integer> callback);

   void getUserSalt(String username, AsyncCallback<String> callback);

   void getUserSalt(AsyncCallback<String> transformAndKeepCallback);

}
