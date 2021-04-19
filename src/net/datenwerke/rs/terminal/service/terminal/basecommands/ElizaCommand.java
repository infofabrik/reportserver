package net.datenwerke.rs.terminal.service.terminal.basecommands;

import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.basecommands.utils.ElizaParse;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.hooks.InteractiveCommandHook;
import net.datenwerke.rs.terminal.service.terminal.locale.TerminalMessages;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

import com.google.inject.Inject;

public class ElizaCommand implements InteractiveCommandHook {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3012114708971363827L;

	public static final String BASE_COMMAND = "eliza";
	
	private final ElizaParse eliza;
	
	private boolean keepInteractiveSession = true;

	@Inject
	public ElizaCommand(ElizaParse eliza){
		this.eliza = eliza;
	}
	
	@Override
	public boolean consumes(CommandParser parser, TerminalSession session) {
		return BASE_COMMAND.equals(parser.getBaseCommand());
	}

	@CliHelpMessage(
		messageClass = TerminalMessages.class,
		name = BASE_COMMAND,
		description = "commandEliza_description"
	)
	@Override
	public CommandResult execute(CommandParser parser, TerminalSession session) {
		return initResult();
	}

	private CommandResult initResult() {
		CommandResult result = new CommandResult();
		
		for(String line : eliza.getIntroMsg())
			result.addResultLine(line);
		
		return result;
	}

	@Override
	public CommandResult executeSubsequent(String command) {
		CommandResult result = new CommandResult();
		if(null != command && command.trim().equals("bye")) {
			keepInteractiveSession = false;
			result.addResultLine("Good Bye");
		} else {
			eliza.handleLine(command);
		
			for(Object line : eliza.msg.toArray())
				result.addResultLine((String)line);
			eliza.msg.clear();
		}
		
		return result;
	}
	
	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
		autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
	}

	@Override
	public boolean isKeepInteractiveSession() {
		return keepInteractiveSession;
	}

	@Override
	public CommandResult ctrlC() {
		keepInteractiveSession = false;
		return new CommandResult("good bye");
	}


	

}
