package net.datenwerke.rs.core.service.jarextension;

import net.datenwerke.gf.service.lateinit.LateInitHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.jarextension.hookers.ReportServerExtenderHooker;

import com.google.inject.Inject;

public class ReportServerExtenderStartup {

	@Inject
	public ReportServerExtenderStartup(
		HookHandlerService hookHandler,
		ReportServerExtenderHooker extender
		){
		hookHandler.attachHooker(LateInitHook.class, extender, HookHandlerService.PRIORITY_HIGH);
		
	}
}
