package net.datenwerke.rs.core.client.userprofile;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.userprofile.client.userprofile.hooks.UserProfileCardProviderHook;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class CoreUserProfileStartup {

	@Inject
	public CoreUserProfileStartup(
		HookHandlerService hookHandler,
		Provider<UserProfileViewContainerHooker> viewContainer
		) {

		
		/* user profile */
		hookHandler.attachHooker(UserProfileCardProviderHook.class, viewContainer);
	}
}
