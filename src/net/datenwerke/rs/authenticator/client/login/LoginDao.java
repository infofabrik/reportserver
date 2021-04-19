package net.datenwerke.rs.authenticator.client.login;

import java.util.Set;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.authenticator.client.login.rpc.LoginHandlerAsync;
import net.datenwerke.security.client.login.AuthToken;
import net.datenwerke.security.client.login.AuthenticateResultDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class LoginDao extends Dao {

	private final LoginHandlerAsync rpcService;

	@Inject
	public LoginDao(LoginHandlerAsync rpcService) {
		super();
		this.rpcService = rpcService;
	}
	
	public void isAuthenticated(AsyncCallback<UserDto> callback){
		rpcService.isAuthenticated(transformDtoCallback(callback));
	}

	public void logoff(AsyncCallback<String> callback){
		rpcService.logoff(transformAndKeepCallback(callback));
	}

	public void authenticate(AuthToken[] tokens, AsyncCallback<AuthenticateResultDto> callback){
		rpcService.authenticate(tokens, transformDtoCallback(callback));
	}

	public void getRequiredClientModules(AsyncCallback<Set<String>> callback){
		rpcService.getRequiredClientModules(transformAndKeepCallback(callback));
	}
	
}
