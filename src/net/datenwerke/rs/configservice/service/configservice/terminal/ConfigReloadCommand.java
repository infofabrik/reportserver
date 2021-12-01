package net.datenwerke.rs.configservice.service.configservice.terminal;

import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.configservice.service.configservice.locale.ConfigMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;

import com.google.inject.Inject;

public class ConfigReloadCommand implements ConfigSubCommandHook {

	public static final String BASE_COMMAND = "reload";
	
	private final ConfigService configService;
	
	@Inject
	public ConfigReloadCommand(
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
		description = "commandConfig_sub_reload_description"
	)
	public CommandResult execute(CommandParser parser, TerminalSession session) throws ObjectResolverException {
		configService.clearCache();
		
		return new CommandResult(ConfigMessages.INSTANCE.configReloaded());
	}

	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
	}
}
