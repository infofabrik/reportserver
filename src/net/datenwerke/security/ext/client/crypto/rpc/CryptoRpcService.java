package net.datenwerke.security.ext.client.crypto.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("crypto")
public interface CryptoRpcService extends RemoteService {

   public String getHmacPassphrase();

   public String getSalt();

   public int getKeyLength();

   public String getUserSalt(String username);

   public String getUserSalt();
}
