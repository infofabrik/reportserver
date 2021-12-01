package net.datenwerke.rs.adminutils.client.datasourcetester;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

import com.google.inject.Inject;

public class DatasourceTesterUIStartup {

	@Inject
	public DatasourceTesterUIStartup(
		HookHandlerService hookHandler,
		
		DatasourceTesterToolbarConfigurator toolbarConfigurator
		){
		
		/* attach configurator */
        hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, toolbarConfigurator);
	}
}
