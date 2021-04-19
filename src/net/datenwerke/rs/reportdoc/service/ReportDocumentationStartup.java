package net.datenwerke.rs.reportdoc.service;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.reportdoc.service.terminal.commands.VariantTestCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;

public class ReportDocumentationStartup {

	@Inject
	public ReportDocumentationStartup(
		HookHandlerService hookHandler,
		
		Provider<VariantTestCommand> variantTestCommand
		){
		
		hookHandler.attachHooker(TerminalCommandHook.class, variantTestCommand);
	}
}
