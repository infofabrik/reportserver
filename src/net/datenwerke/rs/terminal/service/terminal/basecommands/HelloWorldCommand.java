package net.datenwerke.rs.terminal.service.terminal.basecommands;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.locale.TerminalMessages;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.usermanager.entities.User;

public class HelloWorldCommand implements TerminalCommandHook {

	public static final String BASE_COMMAND = "hello";
	
	private final Provider<AuthenticatorService> authenticatorServiceProvider;
	
	@Inject
	public HelloWorldCommand(
		Provider<AuthenticatorService> authenticatorServiceProvider	
		){
		
		/* store objects */
		this.authenticatorServiceProvider = authenticatorServiceProvider;
	}
	
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
	public CommandResult execute(CommandParser parser, TerminalSession session) {
		CommandResult result = new CommandResult();
		
		User user = authenticatorServiceProvider.get().getCurrentUser();
		
		String firstName = user.getFirstname() == null ? "" : user.getFirstname();
		String lastname = user.getLastname() == null ? "" : user.getLastname();
		
		result.addResultLine("Hello " + firstName + " " + lastname);
		
		return result;
	}

	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
		autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
	}

}
