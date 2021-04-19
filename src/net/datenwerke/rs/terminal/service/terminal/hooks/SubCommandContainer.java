package net.datenwerke.rs.terminal.service.terminal.hooks;

import java.util.List;

import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;

public interface SubCommandContainer {

	public abstract String getBaseCommand();
	
	public List<SubCommand> getSubCommands();
	
	public TerminalCommandHook getSubCommand(CommandParser parser, TerminalSession session);
}
