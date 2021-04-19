package net.datenwerke.gf.service.lifecycle.events;

import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

import com.google.inject.Provider;

public class InitSessionEvent extends DwLoggedEvent {

	@Override
	public String getLoggedAction() {
		return "INIT_SESSION";
	}
	
	@Override
	public void setAuthenticatorServiceProvider(
			Provider<AuthenticatorService> authenticatorServiceProvider) {
		// we may not access the session!
	}

}
