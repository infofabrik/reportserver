package net.datenwerke.rs.adminutils.client.logs.terminal.commandproc;

import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;

import net.datenwerke.rs.adminutils.client.logs.dto.ViewLogFileCommandResultExtensionDto;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiService;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;
import net.datenwerke.rs.terminal.client.terminal.hooks.CommandResultProcessorHook;

public class ViewLogFileTerminalCommandProcessor implements CommandResultProcessorHook {

	private final FileServerUiService fileService;

	@Inject
	public ViewLogFileTerminalCommandProcessor(FileServerUiService fileService) {
		this.fileService = fileService;
	}

	@Override
	public void process(CommandResultDto result) {
		if (result.getExtensions().size() == 1
				&& result.getExtensions().get(0) instanceof ViewLogFileCommandResultExtensionDto) {
			final ViewLogFileCommandResultExtensionDto viewCmd = (ViewLogFileCommandResultExtensionDto) result
					.getExtensions().get(0);
			
			List<String> lines = viewCmd.getData();
			
			String data = String.join("\n", lines);
			
			fileService.editFileDirectly(viewCmd.getFilename(), data, false, true, true, true, Optional.empty());
		}
	}

}
