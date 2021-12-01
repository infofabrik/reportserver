package net.datenwerke.rs.installation;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommandContainerImpl;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class PkgCommand extends SubCommandContainerImpl {
	
	private static final String BASE_COMMAND = "pkg";
	private PkgInstallSubCommand installSubCommand;
	private PkgListSubCommand listSubCommand;

	@Inject
	public PkgCommand(PkgInstallSubCommand installSubCommand, PkgListSubCommand listSubCommand) {
		this.installSubCommand = installSubCommand;
		this.listSubCommand = listSubCommand;
	}
	
	@Override
	public boolean consumes(CommandParser parser, TerminalSession session) {
		return BASE_COMMAND.equals(parser.getBaseCommand());
	}

	@Override
	public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
		return super.execute(parser,session);
	}

	@Override
	public String getBaseCommand() {
		return BASE_COMMAND;
	}

	@Override
	public List<SubCommand> getSubCommands() {
		return (List)Arrays.asList(installSubCommand, listSubCommand);
	}

}
