package net.datenwerke.rs.core.client.reportproperties;

import com.google.inject.Inject;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewProviderHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportproperties.hookers.ReportPropertiesViewProviderHooker;

public class ReportPropertiesUiStartup {

	@Inject
	public ReportPropertiesUiStartup(
		HookHandlerService hookHandler,
		
		ReportPropertiesViewProviderHooker mainPanelViewProvider
		){
		
		hookHandler.attachHooker(MainPanelViewProviderHook.class, mainPanelViewProvider, HookHandlerService.PRIORITY_LOW -5);
	
	}
}
