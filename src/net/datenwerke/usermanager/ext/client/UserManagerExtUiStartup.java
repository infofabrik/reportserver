package net.datenwerke.usermanager.ext.client;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.eximport.client.eximport.im.hooks.ImporterConfiguratorHook;
import net.datenwerke.rs.userprofile.client.userprofile.hooks.UserProfileCardProviderHook;
import net.datenwerke.usermanager.ext.client.eximport.im.hookers.UserManagerUIImporterHooker;
import net.datenwerke.usermanager.ext.client.hookers.UserProfileUserDataHooker;

import com.google.inject.Inject;


public class UserManagerExtUiStartup {

	@Inject
	public UserManagerExtUiStartup(
		final HookHandlerService hookHandler,
		final UserManagerUIImporterHooker importerHooker,
		final UserProfileUserDataHooker userProfileDataHooker
		){
		
		/* attach ex/importer */
		hookHandler.attachHooker(ImporterConfiguratorHook.class, importerHooker);
		
		/* attach user profile cards */
		hookHandler.attachHooker(UserProfileCardProviderHook.class, userProfileDataHooker, HookHandlerService.PRIORITY_HIGH);
	}
}
