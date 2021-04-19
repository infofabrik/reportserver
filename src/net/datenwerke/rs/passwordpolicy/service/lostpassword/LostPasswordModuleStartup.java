package net.datenwerke.rs.passwordpolicy.service.lostpassword;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.security.service.authenticator.hooks.PreAuthenticateHook;

import com.google.inject.Inject;

public class LostPasswordModuleStartup {

	@Inject
	public LostPasswordModuleStartup(
			HookHandlerService hookHandler, 
			LostPasswordPreAuthenticateHook lostPasswordPreAuthenticateHook
	) {
		hookHandler.attachHooker(PreAuthenticateHook.class, lostPasswordPreAuthenticateHook);
	}

}
