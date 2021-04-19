package net.datenwerke.rs.eximport.service.eximport.terminal.commands;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.eximport.service.eximport.locale.ExImportMessages;
import net.datenwerke.rs.eximport.service.eximport.terminal.hooks.ImportSubCommandHook;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommandContainerImpl;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

import com.google.inject.Inject;

public class ExportCommand extends SubCommandContainerImpl {

	public static final String BASE_COMMAND = "import";
	
	private final HookHandlerService hookHandler;
	
	@Inject
	public ExportCommand(
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
		messageClass = ExImportMessages.class,
		name = BASE_COMMAND,
		description = "commandImport_description"
	)
	public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
		return super.execute(parser,session);
	}

	@Override
	public List<SubCommand> getSubCommands() {
		List<ImportSubCommandHook> list =  hookHandler.getHookers(ImportSubCommandHook.class);
		return new ArrayList<SubCommand>(list);
	}




}
