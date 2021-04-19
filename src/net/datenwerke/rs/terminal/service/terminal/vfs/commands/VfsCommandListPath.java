package net.datenwerke.rs.terminal.service.terminal.vfs.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.Argument;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultList;
import net.datenwerke.rs.terminal.service.terminal.objresolver.ObjectResolverDeamon;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.VirtualFileSystemDeamon;
import net.datenwerke.rs.terminal.service.terminal.vfs.locale.VfsMessages;
import net.datenwerke.security.service.security.rights.Read;

public class VfsCommandListPath implements TerminalCommandHook  {

	public static final String BASE_COMMAND = "listpath";
	
	@Override
	public boolean consumes(CommandParser parser, TerminalSession session) {
		return BASE_COMMAND.equals(parser.getBaseCommand());
	}

	@CliHelpMessage(
		messageClass = VfsMessages.class,
		name = BASE_COMMAND,
		description = "commandListpath_description",
		args = {
			@Argument(flag="i", description="commandListpath_iflag")
		},
		nonOptArgs = {
			@NonOptArgument(name="arguments", description="commandListpath_objectarg")
		}
	)
	@Override
	public CommandResult execute(CommandParser parser, TerminalSession session) {
		VirtualFileSystemDeamon vfs = session.getFileSystem();
		ObjectResolverDeamon objectResolver = session.getObjectResolver();
		
		List<String> arguments = parser.getNonOptionArguments();
		if(arguments.isEmpty())
			throw new IllegalArgumentException();
		String locationStr = arguments.get(0);
		
		Collection<Object> objects;
		try {
			objects = objectResolver.getObjects(locationStr, Read.class);
		} catch (ObjectResolverException e) {
			throw new IllegalArgumentException(e);
		}
		
		boolean iFlag = parser.hasOption("i");
		
		List<String> paths = new ArrayList<String>();
		for(Object obj : objects){
			VFSLocation location = vfs.getLocationFor(obj);
			if(iFlag)
				paths.add(location.getAbsolutePath());
			else
				paths.add(location.prettyPrint());
		}
		
		CommandResultList list = new CommandResultList(paths);
		list.setDenyBreakUp(true);
		return new CommandResult(list);
	}

	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
		autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
	}

}
