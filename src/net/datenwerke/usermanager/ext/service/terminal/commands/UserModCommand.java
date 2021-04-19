package net.datenwerke.usermanager.ext.service.terminal.commands;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommandContainerImpl;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.security.service.usermanager.locale.UserManagerMessages;
import net.datenwerke.usermanager.ext.service.hooks.UserModSubCommandHook;

import com.google.inject.Inject;


public class UserModCommand extends SubCommandContainerImpl {

	public static final String BASE_COMMAND = "usermod";
	
	private final HookHandlerService hookHandler;
	
	@Inject
	public UserModCommand(
		HookHandlerService hookHandler
		){
		
		/* store objects */
		this.hookHandler = hookHandler;
	}
	
	@Override
	public String getBaseCommand() {
		return BASE_COMMAND;
	}

	@Override
	@CliHelpMessage(
		messageClass = UserManagerMessages.class,
		name = BASE_COMMAND,
		description = "commandUsermod_description"
	)
	public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
		return super.execute(parser,session);
	}

	@Override
	public List<SubCommand> getSubCommands() {
		List<UserModSubCommandHook> list =  hookHandler.getHookers(UserModSubCommandHook.class);
		return new ArrayList<SubCommand>(list);
	}




}
