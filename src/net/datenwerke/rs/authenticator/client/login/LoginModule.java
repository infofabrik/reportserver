package net.datenwerke.rs.authenticator.client.login;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.rs.authenticator.client.login.sessiontimeout.SessionTimeoutWarningDialog;
import net.datenwerke.rs.authenticator.client.login.sessiontimeout.SessionTimeoutWatchdog;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class LoginModule extends AbstractGinModule{

	@Override
	protected void configure() {
		/* bind service */
		bind(LoginService.class).to(LoginServiceImpl.class).in(Singleton.class);
		
		/* bind startup */
		bind(LoginServiceStartup.class).asEagerSingleton();
		
		requestStaticInjection(SessionTimeoutWarningDialog.class);
		requestStaticInjection(SessionTimeoutWatchdog.class);
		requestStaticInjection(AuthenticatorWindow.class);
		
	}
	
}
