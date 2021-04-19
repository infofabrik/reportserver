package net.datenwerke.security.service.authenticator.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.security.client.login.AuthToken;

@HookConfig
public interface PreAuthenticateHook extends Hook {

	public void authenticating(AuthToken[] tokens);
}
