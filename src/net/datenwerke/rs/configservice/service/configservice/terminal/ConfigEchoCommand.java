package net.datenwerke.rs.configservice.service.configservice.terminal;

import net.datenwerke.rs.configservice.service.configservice.locale.ConfigMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.rs.utils.config.ConfigService;

import java.util.List;

import org.apache.commons.configuration2.Configuration;

import com.google.inject.Inject;

public class ConfigEchoCommand implements ConfigSubCommandHook {

	public static final String BASE_COMMAND = "echo";
	
	private final ConfigService configService;
	
	@Inject
	public ConfigEchoCommand(
		ConfigService configService 
		) {
		this.configService = configService;
	}
	
	@Override
	public String getBaseCommand() {
		return BASE_COMMAND;
	}
	
	@Override
	public boolean consumes(CommandParser parser, TerminalSession session) {
		return BASE_COMMAND.equals(parser.getBaseCommand());
	}

	@Override
	@CliHelpMessage(
		messageClass = ConfigMessages.class,
		name = BASE_COMMAND,
		description = "commandConfig_sub_echo_description"
	)
	public CommandResult execute(CommandParser parser, TerminalSession session) throws ObjectResolverException {
		List<String> args = parser.getNonOptionArguments();
		if(args.size() != 2)
			throw new IllegalArgumentException("Expected two arguments: the config file (e.g. main/main.cf) and the property to be read (e.g. default.charset).");
		
		try{
			Configuration config = configService.getConfig(args.get(0));
			return new CommandResult(config.getString(args.get(1)));
		} catch(IllegalArgumentException e){
			return new CommandResult(e.getMessage());
		}
		
	}

	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
	}
}