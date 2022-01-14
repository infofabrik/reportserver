package net.datenwerke.security.client.security.lostpassword.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LostPasswordRpcServiceAsync {

   void requestNewPassword(String username, AsyncCallback<String> callback);

   void isLostPasswordDisabled(AsyncCallback<Boolean> callback);
}
