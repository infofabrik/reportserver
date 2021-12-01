package net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.lfs;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import org.apache.commons.io.FileUtils;

import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.utils.misc.MimeUtils;

public class LfsImportSubCommand implements LfsSubCommandHook {

	public static final String BASE_COMMAND = "import";

	private Provider<MimeUtils> mimeutils;

	@Inject
	public LfsImportSubCommand(Provider<MimeUtils> mimeutils) {
		this.mimeutils = mimeutils;
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
	public CommandResult execute(CommandParser parser, TerminalSession session)	throws TerminalException {
		List<String> noa = parser.getNonOptionArguments();
		if(noa.size() == 2){
			String path = noa.get(0);
			File object = new File(path);
			if(!object.exists())
				return new CommandResult(object.getAbsolutePath() + " does not exist");

			Object baseFolder = session.getObjectResolver().getObject(noa.get(1));
			try {
				copy(object, (FileServerFolder) baseFolder);
			} catch (IOException e) {
				e.printStackTrace();
				return new CommandResult(e);
			}

			return new CommandResult("ok");
		}
		return new CommandResult("Missing argument");
	}

	private FileServerFile getOrCreateFile(String name, FileServerFolder parent){
		for(FileServerFile child : parent.getChildrenOfType(FileServerFile.class)){
			if(child.getName().equals(name))
				return child;
		}

		FileServerFile newFile = new FileServerFile();
		newFile.setName(name);
		newFile.setContentType(mimeutils.get().getMimeTypeByExtension(name));

		parent.addChild(newFile);

		return newFile;
	}

	private FileServerFolder getOrCreateFolder(String name, FileServerFolder parent){
		for(FileServerFolder child : parent.getChildrenOfType(FileServerFolder.class)){
			if(child.getName().equals(name))
				return child;
		}

		FileServerFolder newFile = new FileServerFolder();
		newFile.setName(name);
		parent.addChild(newFile);

		return newFile;
	}

	private void copy(File node, FileServerFolder baseFolder) throws IOException {
		if(node.isFile()){
			byte[] bytes = FileUtils.readFileToByteArray(node);
			FileServerFile file = getOrCreateFile(node.getName(), baseFolder);
			file.setData(bytes);
		}else if(node.isDirectory()){
			FileServerFolder folder = getOrCreateFolder(node.getName(), baseFolder);
			for(File child : node.listFiles()){
				copy(child, folder);
			}
		}
	}

	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
		LfsAutoCompleteHelper.configureAutoComplete(3, autocompleteHelper);
	}

}
