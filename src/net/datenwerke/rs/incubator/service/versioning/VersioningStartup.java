package net.datenwerke.rs.incubator.service.versioning;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.incubator.service.versioning.hooks.RevSubCommandHook;
import net.datenwerke.rs.incubator.service.versioning.terminal.commands.ListSubCommand;
import net.datenwerke.rs.incubator.service.versioning.terminal.commands.RestoreSubCommand;
import net.datenwerke.rs.incubator.service.versioning.terminal.commands.RevCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;

import com.google.inject.Inject;

public class VersioningStartup {
	
	@Inject
	public VersioningStartup(
			HookHandlerService hookHandler,
			RevCommand revCommand,
			ListSubCommand listSubCommand, 
			RestoreSubCommand restoreSubCommand
	) {
		
		hookHandler.attachHooker(TerminalCommandHook.class, revCommand);
		hookHandler.attachHooker(RevSubCommandHook.class, listSubCommand);
		hookHandler.attachHooker(RevSubCommandHook.class, restoreSubCommand);
	}

}
