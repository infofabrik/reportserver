package net.datenwerke.rs.terminal.service.terminal;

import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class ExecuteCommandConfigImpl implements ExecuteCommandConfig{
	
	@Override
	public boolean allowHijackers() {
		return true;
	}

	@Override
	public boolean allowInteractive() {
		return true;
	}

	@Override
	public boolean allowOperators() {
		return true;
	}

	@Override
	public CommandResult execute(TerminalCommandHook commandHooker, CommandParser parser, TerminalSession session) throws TerminalException {
		return commandHooker.execute(parser, session);
	}

}