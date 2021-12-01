package net.datenwerke.rs.incubator.client.reportmetadata;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewProviderHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.incubator.client.reportmetadata.hookers.MainPanelViewProviderHooker;

import com.google.inject.Inject;

public class ReportMetadataUIStartup {

	@Inject
	public ReportMetadataUIStartup(
		HookHandlerService hookHandler,
		
		MainPanelViewProviderHooker mainPanelViewProvider
		){
		
		hookHandler.attachHooker(MainPanelViewProviderHook.class, mainPanelViewProvider, HookHandlerService.PRIORITY_LOW);
	}
}
