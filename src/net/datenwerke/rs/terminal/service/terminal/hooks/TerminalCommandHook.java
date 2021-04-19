package net.datenwerke.rs.terminal.service.terminal.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public interface TerminalCommandHook extends Hook {

	boolean consumes(CommandParser parser, TerminalSession session);

	CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException;

	void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session);

}
