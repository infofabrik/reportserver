package net.datenwerke.rs.terminal.service.terminal.operator;

import com.google.inject.Inject;

import net.datenwerke.async.DwAsyncService;
import net.datenwerke.rs.terminal.service.terminal.ExecuteCommandConfig;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class InBackgroundOperator implements TerminalCommandOperator {

	final private DwAsyncService asyncService;
	
	@Inject
	public InBackgroundOperator(
		DwAsyncService asyncService) {
		this.asyncService = asyncService;
	}

	@Override
	public Integer consumes(String command, CommandParser parser,
			ExecuteCommandConfig config) {
		if(null == command)
			return null;
		
		if(command.trim().endsWith("&"))
			return command.lastIndexOf("&");
		
		return null;
	}

	@Override
	public CommandResult execute(String command, CommandParser parser,
			ExecuteCommandConfig config, final TerminalSession session) {
		final String newCommand = command.substring(0, command.lastIndexOf("&") - 1);
		asyncService.submit(new Runnable() {
			
			@Override
			public void run() {
				try {
					session.execute(newCommand);
				} catch (TerminalException e) {
				}
			}
		});
		return new CommandResult();
	}

}
