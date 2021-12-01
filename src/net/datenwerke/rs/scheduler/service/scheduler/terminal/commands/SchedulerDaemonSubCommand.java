package net.datenwerke.rs.scheduler.service.scheduler.terminal.commands;

import java.util.Arrays;

import javax.inject.Inject;

import net.datenwerke.rs.scheduler.service.scheduler.terminal.hooks.SchedulerSubCommandHook;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.scheduler.service.scheduler.SchedulerService;
import net.datenwerke.scheduler.service.scheduler.exceptions.SchedulerStartupException;

public class SchedulerDaemonSubCommand implements SchedulerSubCommandHook {

	public static final String BASE_COMMAND = "daemon";
	
	private enum Actions {start, stop, restart, enable, disable, status, wdstatus, wdshutdown, wdstart, wdrestart};
	
	private final SchedulerService schedulerService;
	
	@Inject
	public SchedulerDaemonSubCommand(SchedulerService schedulerService) {
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
	public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
		try{
			Actions a = Actions.valueOf(parser.getArgumentNr(1));
			switch(a){
			case start:
				try {
					schedulerService.start();
				} catch (SchedulerStartupException e) {
					throw new RuntimeException(e);
				}
				return returnStatus();
			case stop:
				schedulerService.shutdown();
				return returnStatus();
			case restart:
				try {
					schedulerService.restart();
				} catch (SchedulerStartupException e) {
					throw new RuntimeException(e);
				}
				return returnStatus();
			case enable:
				schedulerService.enable();
				return returnStatus();
			case disable:
				schedulerService.disable();
				return returnStatus();
			case status:
				return returnStatus();
			case wdstatus:
				return watchdogStatus();
			case wdshutdown:
				schedulerService.shutdownWatchdog();
				return watchdogStatus();
			case wdstart:
				if(! schedulerService.isWatchdogActive())
					schedulerService.startWatchdog();
				return watchdogStatus();
			case wdrestart:
				schedulerService.restartWatchdog();
				return watchdogStatus();
			}
			
		}catch(IllegalArgumentException e){
		}

		return new CommandResult("Invalid operation '"+parser.getArgumentNr(1)+"'. Valid operations for scheduler daemon are: " + Arrays.toString(Actions.values()));
	}

	private CommandResult watchdogStatus() {
		String msg = "The scheduler watchdog is " + (schedulerService.isWatchdogActive()?"RUNNING":"TERMINATED");
				return new CommandResult(msg);
	}

	private CommandResult returnStatus(){
		String msg = "The scheduler daemon is " + (schedulerService.isActive()?"RUNNING":"TERMINATED") +
		              " and will " + (schedulerService.isEnabled()?"":"NOT ") + "be started automatically the next time ReportServer is restarted. ";
		return new CommandResult(msg);
	}
	
	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
		for(Actions a : Actions.values()){
			autocompleteHelper.addAutocompleteNamesForToken(3, a.name());
		}
	}

}
