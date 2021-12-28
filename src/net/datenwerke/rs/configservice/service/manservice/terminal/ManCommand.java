package net.datenwerke.rs.configservice.service.manservice.terminal;

import com.google.inject.Inject;

import net.datenwerke.rs.configservice.service.manservice.locale.ManMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.utils.man.ManPageService;


public class ManCommand implements TerminalCommandHook {

	public static final String BASE_COMMAND = "man";
	
	private final ManPageService manpageService;
	
	@Inject
	public ManCommand(ManPageService manpageService) {
		this.manpageService = manpageService;
	}

	@Override
	public boolean consumes(CommandParser parser, TerminalSession session) {
		return BASE_COMMAND.equals(parser.getBaseCommand());
	}

	@Override
	@CliHelpMessage(
		messageClass = ManMessages.class,
		name = BASE_COMMAND,
		description = "commandMan_description"
	)
	public CommandResult execute(CommandParser parser, TerminalSession session) {
		String arg = parser.getArgumentString();
		
		String manPage = manpageService.getManPageFailsafe("man/" + arg);
		if(null == manPage)
			return new CommandResult("Could not find manpage for: " + arg);
		
		return new CommandResult(manPage);
	}

	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
		autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
	}




}
