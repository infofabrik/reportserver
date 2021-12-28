package net.datenwerke.security.ext.client.crypto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CryptoUIService {

   public void getHmacPassphrase(AsyncCallback<String> callback);

   public void getUserPassphrase(String password, AsyncCallback<String> callback);

   public void encrypt(String data, String password, AsyncCallback<String> callback);

   public void encrypt(String username, String data, String password, AsyncCallback<String> callback);
}
