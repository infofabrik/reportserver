package net.datenwerke.rs.fileserver.service.fileserver.terminal.commands;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.fileserver.service.fileserver.locale.FileserverMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.hooks.PipedTerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.security.service.security.rights.Read;

import com.google.inject.Inject;

public class ZipCommand implements TerminalCommandHook, PipedTerminalCommandHook {

	private static final String BASE_COMMAND = "zip";

	private final ZipUtilsService zipUtilsService;
	private final FileServerService fileServerService;

	@Inject
	public ZipCommand(
			ZipUtilsService zipUtilsService, 
			FileServerService fileServerService
			) {

		this.zipUtilsService = zipUtilsService;
		this.fileServerService = fileServerService;
	}

	@Override
	public boolean consumes(CommandParser parser, TerminalSession session) {
		return BASE_COMMAND.equals(parser.getBaseCommand());
	}

	@Override
	@CliHelpMessage(
			messageClass = FileserverMessages.class,
			name = BASE_COMMAND,
			description = "commandZip_description"
			)
	public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
		List<String> arguments = parser.getNonOptionArguments();
		Collection<Object> objects = session.getObjectResolver().getObjects(arguments.get(1), Read.class);

		HashMap<String, Object> zipObjects = new HashMap<String, Object>(); 

		VFSLocation currentLocation = session.getFileSystem().getCurrentLocation();
		Object clObject;
		try {
			clObject = currentLocation.getObject();
		} catch (VFSException e) {
			throw new TerminalException(e);
		}
		FileServerFolder currentFolder = null;
		if(clObject instanceof FileServerFolder){
			currentFolder = (FileServerFolder) clObject;
		}else{
			throw new TerminalException("Can't do this here. ");
		}

		for(Object o : objects){
			if(o instanceof FileServerFile){
				addFile((FileServerFile) o, currentFolder, zipObjects);
			}
			if(o instanceof FileServerFolder){
				addFolder((FileServerFolder) o, currentFolder, zipObjects);
			}
		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			zipUtilsService.createZip(zipObjects, bos);
		} catch (IOException e) {
			throw new TerminalException(e.getMessage());
		}

		FileServerFile fileAtLocation = fileServerService.createFileAtLocation(arguments.get(0));
		fileAtLocation.setContentType("application/zip");
		fileAtLocation.setData(bos.toByteArray());
		fileServerService.merge(fileAtLocation);

		CommandResult cr = new CommandResult();
		for(String f : zipObjects.keySet()){
			cr.addResultLine(f);
		}

		return cr;
	}

	private void addFile(FileServerFile file, FileServerFolder baseFolder, Map<String, Object> zipObjects){
		zipObjects.put(getRealtivePath(file, baseFolder), file.getData());
	}

	private void addFolder(FileServerFolder folder, FileServerFolder baseFolder, Map<String, Object> zipObjects){
		zipObjects.put(getRealtivePath(folder, baseFolder), ZipUtilsService.DIRECTORY_MARKER);
		for(AbstractFileServerNode f : folder.getChildren()){
			if(f instanceof FileServerFolder){
				addFolder((FileServerFolder) f, baseFolder, zipObjects);
			}else if(f instanceof FileServerFile){
				addFile((FileServerFile) f, baseFolder, zipObjects);
			}
		}

	}

	private String getRealtivePath(AbstractFileServerNode node, AbstractFileServerNode root){
		if(node == root){
			return "";
		}else{
			String name = "";
			if(node instanceof FileServerFile)
				name = ((FileServerFile) node).getName();
			if(node instanceof FileServerFolder)
				name = ((FileServerFolder) node).getName();

			if(null != node.getParent()){
				String rp = getRealtivePath(node.getParent(), root);
				return ("".equals(rp)?name: rp + "/" + name);
			}else{
				return name;

			}
		}
	}

	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
		autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
	}

	@Override
	public CommandResult execute(CommandResult lastResult,
			CommandParser parser, TerminalSession session)
					throws TerminalException {
		String data = lastResult.toString();

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			zipUtilsService.createZip(data.getBytes(), bos);
		} catch (IOException e) {
			throw new TerminalException(e.getMessage());
		}

		return new CommandResult(bos.toByteArray());
	}

}
