package net.datenwerke.rs.userprofile.client.userprofile;

import net.datenwerke.gf.client.homepage.hooks.HomepageHeaderContentHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.userprofile.client.userprofile.hookers.HomepageProfileHooker;

import com.google.inject.Inject;

/**
 * 
 *
 */
public class UserProfileUIStartup {

	@Inject
	public UserProfileUIStartup(
		final HookHandlerService hookHandler,
		HomepageProfileHooker profileHooker	
		){
		
		/* attach hooks */
		hookHandler.attachHooker(HomepageHeaderContentHook.class, profileHooker, HookHandlerService.PRIORITY_HIGH);
	}
}
