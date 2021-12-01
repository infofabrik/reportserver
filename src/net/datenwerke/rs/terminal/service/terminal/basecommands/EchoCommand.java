package net.datenwerke.rs.terminal.service.terminal.basecommands;

import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.hooks.PipedTerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.locale.TerminalMessages;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class EchoCommand implements PipedTerminalCommandHook{

	public static final String BASE_COMMAND = "echo";
	
	@Override
	public boolean consumes(CommandParser parser, TerminalSession session) {
		return BASE_COMMAND.equals(parser.getBaseCommand());
	}

	@CliHelpMessage(
		messageClass = TerminalMessages.class,
		name = BASE_COMMAND,
		description = "commandHelloWorld_description"
	)
	@Override
	public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
		return execute(null, parser, session);
	}
	
	@Override
	public CommandResult execute(CommandResult lastResult,
			CommandParser parser, TerminalSession session)
			throws TerminalException {
		if(null == lastResult){
			return new CommandResult(parser.getArgumentString());
		} 
		return new CommandResult(lastResult.toString());
	}

	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
		autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
	}

	

}
