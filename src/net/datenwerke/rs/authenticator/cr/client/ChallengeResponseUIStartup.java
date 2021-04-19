package net.datenwerke.rs.authenticator.cr.client;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.authenticator.client.login.hooks.ClientPAMHook;
import net.datenwerke.rs.authenticator.cr.client.pam.ChallengeResponseClientPam;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ChallengeResponseUIStartup {

	@Inject
	public ChallengeResponseUIStartup(
			Provider<ChallengeResponseClientPam> challengeResponseClientPamProvider, 
			HookHandlerService hookHandler
		) {
		
		hookHandler.attachHooker(ClientPAMHook.class, new ClientPAMHook(challengeResponseClientPamProvider));
	}
}
