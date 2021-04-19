package net.datenwerke.rs.passwordpolicy.client.activateuser.rpc;

import net.datenwerke.security.client.usermanager.dto.UserDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ActivateUserRpcServiceAsync {

	void activateAccount(UserDto user, boolean force,
			AsyncCallback<Void> callback);

}
