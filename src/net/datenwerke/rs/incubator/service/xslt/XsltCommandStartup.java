package net.datenwerke.rs.incubator.service.xslt;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.incubator.service.xslt.terminal.commands.XsltCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class XsltCommandStartup {

	@Inject
	public XsltCommandStartup(
		HookHandlerService hookHandler,
		Provider<XsltCommand> xsltProvider
		){
		
		hookHandler.attachHooker(TerminalCommandHook.class, xsltProvider);
	}
}
