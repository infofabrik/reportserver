package net.datenwerke.rs.authenticator.client.login.rpc;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.security.client.login.AuthToken;
import net.datenwerke.security.client.login.AuthenticateResultDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;

public interface LoginHandlerAsync {

   void authenticate(AuthToken[] tokens, AsyncCallback<AuthenticateResultDto> callback);

   void getRequiredClientModules(AsyncCallback<Set<String>> callback);

   void getSessionTimeout(AsyncCallback<Integer> callback);

   void isAuthenticated(AsyncCallback<UserDto> callback);

   void logoff(AsyncCallback<String> callback);

}
