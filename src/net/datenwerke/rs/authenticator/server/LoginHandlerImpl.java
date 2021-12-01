package net.datenwerke.rs.authenticator.server;

import java.util.Set;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.authenticator.client.login.rpc.LoginHandler;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.security.client.login.AuthToken;
import net.datenwerke.security.client.login.AuthenticateResultDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.authenticator.AuthenticationResult;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.usermanager.entities.User;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

@Singleton
public class LoginHandlerImpl extends SecuredRemoteServiceServlet implements LoginHandler{

	private static final long serialVersionUID = -295811449594943047L;

	public static final String LOGOUT_URL_PROPERTY = "logout.url";
	public static final String CONFIG_FILE = "security/misc.cf";

	private final Provider<AuthenticatorService> authenticatorService;
	private final ConfigService configService;
	private final DtoService dtoService;


	@Inject
	public LoginHandlerImpl(
			Provider<AuthenticatorService> authenticatorService, 
			ConfigService configService,
			DtoService dtoService
			) {
		super();

		this.authenticatorService = authenticatorService;
		this.configService = configService;
		this.dtoService = dtoService;
	}

	@Override
	@Transactional(rollbackOn={Exception.class})
	@SecurityChecked(loginRequired=false)
	public Set<String> getRequiredClientModules() throws ServerCallFailedException {
		return authenticatorService.get().getRequiredClientModules();
	}

	@Override
	@Transactional(rollbackOn={Exception.class})
	@SecurityChecked(loginRequired=false)
	public AuthenticateResultDto authenticate(AuthToken[] tokens) throws ServerCallFailedException {
		AuthenticationResult authRes = authenticatorService.get().authenticate(tokens);
		AuthenticateResultDto ares = null;
		if(authRes.isAllowed() && null != authRes.getUser()){
			ares = new AuthenticateResultDto(true, getCurrentUser());
		}else{
			ares = new AuthenticateResultDto(false, null);
		}
		ares.setInfo(authRes.getInfos());
		return ares;
	}

	@Override
	@Transactional(rollbackOn={Exception.class})
	@SecurityChecked(loginRequired=false)
	public UserDto isAuthenticated() throws ServerCallFailedException  {
		AuthenticatorService authService = authenticatorService.get();
		if(authService.isAuthenticated()){
			UserDto currentUser = null;
			try{
				currentUser = getCurrentUser();
			}catch(Exception e){}

			return currentUser;
		} else {
			return null;
		}
	}

	protected UserDto getCurrentUser() throws ServerCallFailedException  {
		User u = authenticatorService.get().getCurrentUser();
		return (UserDto) dtoService.createDto(u);
	}

	@Transactional(rollbackOn={Exception.class})
	@Override
	@SecurityChecked(bypass=true)
	public String logoff() throws ServerCallFailedException {
		authenticatorService.get().logoff();

		String logoutURL = configService.getConfigFailsafe(CONFIG_FILE).getString(LOGOUT_URL_PROPERTY, null);

		return logoutURL;
	}

	@Override
	@Transactional(rollbackOn={Exception.class})
	@SecurityChecked(bypass = true)
	public int getSessionTimeout() throws ServerCallFailedException {
		return this.getThreadLocalRequest().getSession().getMaxInactiveInterval();
	}

}
