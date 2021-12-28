package net.datenwerke.rs.passwordpolicy.client.activateuser.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.security.client.usermanager.dto.UserDto;

public interface ActivateUserRpcServiceAsync {

	void activateAccount(UserDto user, boolean force,
			AsyncCallback<Void> callback);

}
