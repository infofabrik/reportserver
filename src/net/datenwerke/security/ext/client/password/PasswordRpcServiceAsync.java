package net.datenwerke.security.ext.client.password;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface PasswordRpcServiceAsync {

	void changePassword(String username, String oldPassword,
			String newPassword, boolean encrypted, AsyncCallback<Void> callback);

	void changePassword(String oldPassword, String newPassword,
			boolean encrypted, AsyncCallback<Void> callback);


}
