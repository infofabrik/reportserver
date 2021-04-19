package net.datenwerke.rs.configservice.service.manservice;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.configservice.service.manservice.terminal.DocCommand;
import net.datenwerke.rs.configservice.service.manservice.terminal.DocReloadCommand;
import net.datenwerke.rs.configservice.service.manservice.terminal.DocSubCommandHook;
import net.datenwerke.rs.configservice.service.manservice.terminal.ManCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ManPageStartup {

	@Inject
	public ManPageStartup(
		HookHandlerService hookHandlerService,
		
		Provider<ManCommand> manCommand,
		Provider<DocCommand> docCommand,
		Provider<DocReloadCommand> manReloadCommand
		) {
		
		hookHandlerService.attachHooker(TerminalCommandHook.class, manCommand);
		hookHandlerService.attachHooker(TerminalCommandHook.class, docCommand);
		hookHandlerService.attachHooker(DocSubCommandHook.class, manReloadCommand);
	}
}
