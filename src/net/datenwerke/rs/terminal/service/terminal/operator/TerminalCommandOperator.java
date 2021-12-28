package net.datenwerke.rs.terminal.service.terminal.operator;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.terminal.service.terminal.ExecuteCommandConfig;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public interface TerminalCommandOperator extends Hook {

   Integer consumes(String command, CommandParser parser, ExecuteCommandConfig config);

   CommandResult execute(String command, CommandParser parser, ExecuteCommandConfig config, TerminalSession session)
         throws TerminalException;

}
