package net.datenwerke.rs.terminal.service.terminal.vfs.commands;

import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.Argument;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.vfs.VirtualFileSystemDeamon;
import net.datenwerke.rs.terminal.service.terminal.vfs.locale.VfsMessages;

public class VfsCommandPwd implements TerminalCommandHook {

	public static final String BASE_COMMAND = "pwd";
	
	@Override
	public boolean consumes(CommandParser parser, TerminalSession session) {
		return BASE_COMMAND.equals(parser.getBaseCommand());
	}

	@CliHelpMessage(
			messageClass = VfsMessages.class,
			name = BASE_COMMAND,
			description = "commandPwd_description",
			args = {
				@Argument(flag="i", hasValue=false, description="commandPwd_argument")
			}
		)
	@Override
	public CommandResult execute(CommandParser parser, TerminalSession session) {
		VirtualFileSystemDeamon vfs = session.getFileSystem();
		if(parser.hasOption("i"))
			return new CommandResult(vfs.getCurrentPath());
		return new CommandResult(vfs.prettyPrintCurrentPath());
	}

	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
		autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
	}

}
