package net.datenwerke.rs.incubator.service.outputformatauth.hookers;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import net.datenwerke.rs.base.client.AvailableReportProperties;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportProperty;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportStringProperty;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportExecutionNotificationHook;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.incubator.service.outputformatauth.locale.OutputFormatAuthMessages;
import net.datenwerke.security.service.usermanager.entities.User;

public class OutputFormatAuthWatchDog implements
		ReportExecutionNotificationHook {

	@Override
	public void notifyOfReportExecution(Report report,
			ParameterSet parameterSet, User user, String outputFormat,
			ReportExecutionConfig[] configs) throws ReportExecutorException {

	}

	@Override
	public void notifyOfReportsSuccessfulExecution(
			CompiledReport compiledReport, Report report,
			ParameterSet parameterSet, User user, String outputFormat,
			ReportExecutionConfig[] configs) {

	}

	@Override
	public void notifyOfReportsUnsuccessfulExecution(ReportExecutorException e,
			Report report, ParameterSet parameterSet, User user,
			String outputFormat, ReportExecutionConfig[] configs) {

	}

	@Override
	public void doVetoReportExecution(Report report, ParameterSet parameterSet,
			User user, String outputFormat, ReportExecutionConfig[] configs) throws ReportExecutorException {
		
		ReportProperty property = report.getEffectiveReportProperty(AvailableReportProperties.PROPERTY_OUTPUT_FORMAT_AUTH.getValue());
		
		/* always allow certain formats */
		if(ReportExecutorService.METADATA_FORMAT_PLAIN.equals(outputFormat) ||
				ReportExecutorService.OUTPUT_FORMAT_DATACOUNT.equals(outputFormat) ||
				ReportExecutorService.OUTPUT_FORMAT_METADATA.equals(outputFormat) ||
				ReportExecutorService.OUTPUT_FORMAT_TABLE.equals(outputFormat) ){
			return;
		}
		
		if(property instanceof ReportStringProperty){
			String formatString = ((ReportStringProperty) property).getStrValue();
			if(null != formatString){
				List<String> formats = Arrays.asList(StringUtils.split(formatString,','));
				if(! formats.contains(outputFormat))
					throw new ReportExecutorException(OutputFormatAuthMessages.INSTANCE.exceptionInvalidFormat(outputFormat));
			}
		}
	}

}
