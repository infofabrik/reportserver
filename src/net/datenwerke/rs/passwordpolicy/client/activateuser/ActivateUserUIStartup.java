package net.datenwerke.rs.passwordpolicy.client.activateuser;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.passwordpolicy.client.activateuser.hooks.ActivateUserToolbarConfigurator;

import com.google.inject.Inject;

public class ActivateUserUIStartup {
	
	@Inject
	public ActivateUserUIStartup(
		HookHandlerService hookHandler, 
			
		ActivateUserToolbarConfigurator activateUserToolbarConfigurator
		) {
		
		hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, activateUserToolbarConfigurator);
		
	}

}
