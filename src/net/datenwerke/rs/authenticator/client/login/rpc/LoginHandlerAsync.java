package net.datenwerke.rs.authenticator.client.login.rpc;

import java.util.Set;

import net.datenwerke.security.client.login.AuthToken;
import net.datenwerke.security.client.login.AuthenticateResultDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginHandlerAsync {

	void authenticate(AuthToken[] tokens,
			AsyncCallback<AuthenticateResultDto> callback);

	void getRequiredClientModules(AsyncCallback<Set<String>> callback);

	void getSessionTimeout(AsyncCallback<Integer> callback);

	void isAuthenticated(AsyncCallback<UserDto> callback);

	void logoff(AsyncCallback<String> callback);

}
