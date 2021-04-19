package net.datenwerke.rs.core.service.reportserver;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.security.service.authenticator.hooks.PostAuthenticateHook;

import com.google.inject.Inject;

public class ReportServerServiceStartup {

	@Inject
	public ReportServerServiceStartup(
		HookHandlerService hookHandler,
		ServerInformationGathererHack serverInformationGathererHack
		){
		hookHandler.attachHooker(PostAuthenticateHook.class, serverInformationGathererHack);
	}
}
