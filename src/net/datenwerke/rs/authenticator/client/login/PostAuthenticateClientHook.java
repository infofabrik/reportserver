package net.datenwerke.rs.authenticator.client.login;

import java.util.List;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.security.client.login.AuthenticateResultDto;

public interface PostAuthenticateClientHook extends Hook {
	
	public void authenticated(AuthenticateResultDto authRes, List<PostAuthenticateClientHook> chain);

}
