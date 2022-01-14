package net.datenwerke.security.service.authenticator.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.security.service.authenticator.AuthenticationResult;

@HookConfig
public interface PostAuthenticateHook extends Hook {

   public void authenticated(AuthenticationResult authRes);

}
