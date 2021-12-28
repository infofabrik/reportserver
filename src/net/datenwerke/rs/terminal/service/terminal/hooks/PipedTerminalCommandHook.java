package net.datenwerke.rs.terminal.service.terminal.hooks;

import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public interface PipedTerminalCommandHook extends TerminalCommandHook {

   CommandResult execute(CommandResult lastResult, CommandParser parser, TerminalSession session)
         throws TerminalException;
}
