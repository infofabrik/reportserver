package net.datenwerke.rs.terminal.service.terminal.vfs.commands;

import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.vfs.VirtualFileSystemDeamon;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;

import com.google.inject.Inject;

public class VfsCommandCd implements TerminalCommandHook {


	public static final String BASE_COMMAND = "cd";
	
	
	@Inject
	public VfsCommandCd(
		){
	}
	
	@Override
	public boolean consumes(CommandParser parser, TerminalSession session) {
		return BASE_COMMAND.equals(parser.getBaseCommand());
	}

	@Override
	public CommandResult execute(CommandParser parser, TerminalSession session) {
		String path = parser.getArgumentNr(1);
		if(null == path || "".equals(path))
			return new CommandResult("no path specified");
		
		VirtualFileSystemDeamon vfs = session.getFileSystem();
		try {
			vfs.cd(path);
		} catch (VFSException e) {
			return new CommandResult(e.getMessage());
		}
		
		return CommandResult.createEmptyInstance();
	}

	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
		autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
	}

}
