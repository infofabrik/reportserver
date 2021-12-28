package net.datenwerke.rs.scheduler.service.scheduler.terminal.commands;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.rs.scheduler.service.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.service.scheduler.terminal.hooks.SchedulerSubCommandHook;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.scheduler.service.scheduler.SchedulerService;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;


public class SchedulerRemoveSubCommand implements SchedulerSubCommandHook{

	public static final String BASE_COMMAND = "remove";
	
	private final SchedulerService schedulerService;

	@Inject
	public SchedulerRemoveSubCommand(
		SchedulerService schedulerService
		){
		
		/* store objects */
		this.schedulerService = schedulerService;
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
	@CliHelpMessage(
		messageClass = SchedulerMessages.class,
		name = BASE_COMMAND,
		description = "commandScheduler_sub_remove_description",
		nonOptArgs = {
			@NonOptArgument(name="jobId", description="commandScheduler_sub_remove_arg1", mandatory=true)
		}
	)
	public CommandResult execute(CommandParser parser, TerminalSession session) throws ObjectResolverException {
		List<String> args = parser.getNonOptionArguments();
		if(args.size() != 1)
			throw new IllegalArgumentException("Expected exactly one argument");
		
		Long id = Long.valueOf(args.get(0));
		AbstractJob job = schedulerService.getJobById(id);
		if(null == job)
			return new CommandResult("Could not find job with id: " + id);
		
		schedulerService.remove(job);
		
		return new CommandResult("removed job: " + id);
	}

	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
	}

}
