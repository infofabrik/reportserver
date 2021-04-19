package net.datenwerke.rs.fileserver.service.fileserver.terminal.commands;

import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocationInfo;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSObjectInfo;
import net.datenwerke.rs.terminal.service.terminal.vfs.VirtualFileSystemDeamon;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.rs.terminal.service.terminal.vfs.helper.PathHelper;
import net.datenwerke.rs.terminal.service.terminal.vfs.locale.VfsMessages;

public class EditTextFileCommand implements TerminalCommandHook {


	private static final String BASE_COMMAND = "editTextFile";


	@Override
	public boolean consumes(CommandParser parser, TerminalSession session) {
		return BASE_COMMAND.equals(parser.getBaseCommand());
	}

	@Override
	public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
		String completePath = parser.getArgumentNr(1);
		
		if (null == completePath)
			throw new IllegalArgumentException("Expected filename");
		
		PathHelper helper = new PathHelper(completePath);
		String path = helper.getPath();
		String fileName = helper.getLastPathway();
		
		if(null == fileName || "".equals(fileName.trim()))
			throw new IllegalArgumentException("Expected filename");
		
		VFSLocation location;
		if("".equals(path))
			location = session.getFileSystem().getCurrentLocation();
		else {
			try {
				location = session.getFileSystem().getLocation(path);
			} catch (VFSException e1) {
				throw new IllegalArgumentException("illegal path: " + path);
			}
		}
		/* check if file exists */
		VirtualFileSystemDeamon vfs = session.getFileSystem();
		VFSLocationInfo locationInfo = vfs.getLocationInfo(location);
		boolean found = false;
		for(VFSObjectInfo info : locationInfo.getChildInfos()){
			if(fileName.equals(info.getName()))
				found = true;
		}
		if (!found) 
			return  new CommandResult("No file found with the given name");
		
		Object node = session.getObjectResolver().getObjects(parser.getArgumentNr(1)).iterator().next();
		
		if(null == node)
			return new CommandResult(VfsMessages.INSTANCE.fileNotFound());
		
		if(! (node instanceof FileServerFile))
			return new CommandResult(VfsMessages.INSTANCE.notSupported());
		
		CommandResult result = new CommandResult();
		EditCommandResultExtension ext = new EditCommandResultExtension();
		ext.setFile((FileServerFile)node);
		ext.setData(null == ((FileServerFile)node).getData() ? "" : new String(((FileServerFile)node).getData()));
		result.addExtension(ext);
		
		return result;
	}
	


	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
		autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
	}

}
