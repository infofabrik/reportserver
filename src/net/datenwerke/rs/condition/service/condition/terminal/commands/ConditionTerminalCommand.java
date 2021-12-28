package net.datenwerke.rs.condition.service.condition.terminal.commands;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.condition.service.condition.locale.ConditionMessages;
import net.datenwerke.rs.condition.service.condition.terminal.commands.hooks.ConditionSubCommandHook;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommandContainerImpl;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class ConditionTerminalCommand extends SubCommandContainerImpl {

public static final String BASE_COMMAND = "rcondition";
	
	private final HookHandlerService hookHandler;
	
	@Inject
	public ConditionTerminalCommand(
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
		messageClass = ConditionMessages.class,
		name = BASE_COMMAND,
		description = "commandRcondition_description"
	)
	public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
		return super.execute(parser,session);
	}

	@Override
	public List<SubCommand> getSubCommands() {
		List<ConditionSubCommandHook> list =  hookHandler.getHookers(ConditionSubCommandHook.class);
		return new ArrayList<SubCommand>(list);
	}
}
