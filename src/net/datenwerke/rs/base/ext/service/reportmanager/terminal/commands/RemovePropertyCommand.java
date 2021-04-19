package net.datenwerke.rs.base.ext.service.reportmanager.terminal.commands;

import java.util.List;

import net.datenwerke.rs.base.ext.service.reportmanager.hooks.ReportModSubCommandHook;
import net.datenwerke.rs.base.ext.service.reportmanager.locale.ReportManagerExtMessages;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportProperty;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.security.service.security.rights.Read;

import com.google.inject.Inject;

public class RemovePropertyCommand implements ReportModSubCommandHook {

	public static final String BASE_COMMAND = "removeProperty";
	
	private final ReportService reportService;

	@Inject
	public RemovePropertyCommand(
			ReportService reportService
		){
		
		/* store objects */
		this.reportService = reportService;
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
		messageClass = ReportManagerExtMessages.class,
		name = BASE_COMMAND,
		description = "commandReportmod_sub_removeProperty_description",
		nonOptArgs = {
			@NonOptArgument(name="identifier", description="commandReportmod_sub_removeProperty_arg1", mandatory=true),
			@NonOptArgument(name="property", description="commandReportmod_sub_removeProperty_arg2", mandatory=true)
		}
	)
	public CommandResult execute(CommandParser parser, TerminalSession session) throws ObjectResolverException {
		List<String> arguments = parser.getNonOptionArguments();
		if(2 > arguments.size())
			throw new IllegalArgumentException();
		
		String reportRef = arguments.get(0);
		String propertyName = arguments.get(1);
		
		try{
			Report report = (Report) session.getObjectResolver().getObject(reportRef,Read.class);
			if(null == report)
				return new CommandResult("Coud not find report for " + reportRef);
			
			ReportProperty property = report.getReportProperty(propertyName);
			if(null != property)
				reportService.remove(report, property);
			
			return new CommandResult("Property removed");
		} catch(Exception e){
			return new CommandResult("Coud not find report for " + reportRef);
		}
	}

	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
	}
}
