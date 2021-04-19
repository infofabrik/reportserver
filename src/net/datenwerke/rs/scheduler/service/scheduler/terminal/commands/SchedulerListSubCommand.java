package net.datenwerke.rs.scheduler.service.scheduler.terminal.commands;

import java.text.DateFormat;
import java.util.Arrays;

import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.scheduler.service.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.service.scheduler.terminal.hooks.SchedulerSubCommandHook;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.scheduler.service.scheduler.SchedulerService;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.JobFilterConfiguration;

import com.google.inject.Inject;


public class SchedulerListSubCommand implements SchedulerSubCommandHook{

	public static final String BASE_COMMAND = "list";
	
	private final SchedulerService schedulerService;

	@Inject
	public SchedulerListSubCommand(
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
		description = "commandScheduler_sub_list_description"
	)
	public CommandResult execute(CommandParser parser, TerminalSession session) throws ObjectResolverException {
		JobFilterConfiguration filter = new JobFilterConfiguration();
		
		if(parser.hasOption("i")){
			filter.setActive(false);
			filter.setInActive(true);
		} else {
			filter.setActive(true);
			filter.setInActive(false);
		}
		
		RSTableModel table = new RSTableModel();
		table.setTableDefinition(new TableDefinition(Arrays.asList(new String[]{"id", "type", "next firetime"})));
		for (AbstractJob job : schedulerService.getJobsBy(filter)){
			table.addDataRow(new RSStringTableRow(
				String.valueOf(job.getId()), 
				job.getClass().getSimpleName(),
				null != job.getTrigger().getNextScheduledFireTime() ?
						DateFormat.getDateTimeInstance().format(job.getTrigger().getNextScheduledFireTime()) : ""
			));
		}
		
		return new CommandResult(table);
	}

	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
	}

}
