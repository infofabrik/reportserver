package net.datenwerke.rs.fileserver.client.fileserver.terminal.commandproc;

import com.google.inject.Inject;

import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiService;
import net.datenwerke.rs.fileserver.client.fileserver.dto.EditCommandResultExtensionDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;
import net.datenwerke.rs.terminal.client.terminal.hooks.CommandResultProcessorHook;

public class EditTerminalCommandProcessor implements CommandResultProcessorHook {
	
	private final FileServerUiService fileService;
	
	@Inject
	public EditTerminalCommandProcessor(
		FileServerUiService fileService
			) {
		this.fileService = fileService;
	}

	@Override
	public void process(CommandResultDto result) {
		if(result.getExtensions().size() == 1 && result.getExtensions().get(0) instanceof EditCommandResultExtensionDto){
			final EditCommandResultExtensionDto editCmd = (EditCommandResultExtensionDto) result.getExtensions().get(0);
			final FileServerFileDto file = editCmd.getFile(); 
			
			fileService.editFileDirectly(file, true, false, false, false, editCmd.getData());
		}
	}

}
