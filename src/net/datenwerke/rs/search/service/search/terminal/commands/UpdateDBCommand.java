package net.datenwerke.rs.search.service.search.terminal.commands;

import net.datenwerke.rs.search.service.search.SearchService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

import com.google.inject.Inject;

public class UpdateDBCommand implements TerminalCommandHook {
	
	private final static String BASE_COMMAND = "updatedb";
	
	private final SearchService searchService;
	
	@Inject
	public UpdateDBCommand(SearchService searchService) {
		this.searchService = searchService;
	}

	@Override
	public boolean consumes(CommandParser parser, TerminalSession session) {
		return BASE_COMMAND.equals(parser.getBaseCommand());
	}

	@Override
	public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
		CommandResult cr = new CommandResult();
		cr.addResultLine(searchService.rebuildIndex());
		return cr;
	}

	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
		autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
	}
	
	

}
