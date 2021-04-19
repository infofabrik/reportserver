package net.datenwerke.rs.terminal.service.terminal;

import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public interface ExecuteCommandConfig{
	boolean allowHijackers();
	boolean allowInteractive();
	boolean allowOperators();
	CommandResult execute(TerminalCommandHook commandHook, CommandParser parser, TerminalSession session) throws TerminalException;
}