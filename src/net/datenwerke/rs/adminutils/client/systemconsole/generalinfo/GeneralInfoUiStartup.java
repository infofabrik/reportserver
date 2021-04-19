package net.datenwerke.rs.adminutils.client.systemconsole.generalinfo;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.hooks.GeneralInfoSystemConsoleHooker;
import net.datenwerke.rs.adminutils.client.systemconsole.hooks.SystemConsoleViewDomainHook;

public class GeneralInfoUiStartup {

	@Inject
	public GeneralInfoUiStartup(final HookHandlerService hookHandler,
			GeneralInfoSystemConsoleHooker generalTargetDomain) {
		
		hookHandler.attachHooker(SystemConsoleViewDomainHook.class, generalTargetDomain,  HookHandlerService.PRIORITY_HIGH);

	}
}
