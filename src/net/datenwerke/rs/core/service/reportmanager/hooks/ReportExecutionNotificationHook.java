package net.datenwerke.rs.core.service.reportmanager.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.ReportEngine;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * Hook used by the {@link ReportEngine}. It allows to be notified of report execution events,
 * and can be used to stop the execution of a report.
 *
 */
@HookConfig
public interface ReportExecutionNotificationHook extends Hook {

	/**
	 * Called before a report is about to be executed. This method should not throw any exceptions
	 * and be used only for notifaction purposes. If you want to stop the execution of a report,
	 * use the {@link #doVetoReportExecution(Report, ParameterSet, User, String, ReportExecutionConfig[])}
	 * method instead.
	 *  
	 * 
	 * @param report
	 * @param parameterSet
	 * @param user
	 * @param outputFormat
	 * @param configs
	 * @throws ReportExecutorException
	 */
	void notifyOfReportExecution(Report report, ParameterSet parameterSet, User user, String outputFormat, ReportExecutionConfig[] configs) throws ReportExecutorException;

	/**
	 * Called after successful completion of a report execution. 
	 * 
	 * @param compiledReport
	 * @param report
	 * @param parameterSet
	 * @param user
	 * @param outputFormat
	 * @param configs
	 */
	void notifyOfReportsSuccessfulExecution(CompiledReport compiledReport,
			Report report, ParameterSet parameterSet, User user,
			String outputFormat, ReportExecutionConfig[] configs);

	/**
	 * Called after an unsuccessful execution of a report.
	 * 
	 * @param e
	 * @param report
	 * @param parameterSet
	 * @param user
	 * @param outputFormat
	 * @param configs
	 */
	void notifyOfReportsUnsuccessfulExecution(ReportExecutorException e,
			Report report, ParameterSet parameterSet, User user,
			String outputFormat, ReportExecutionConfig[] configs);

	/**
	 * Called before the execution of a report and meant to allow to veto (stop) that execution. In order
	 * to stop an execution, throw a {@link ReportExecutorException}.
	 * 
	 * @param report
	 * @param parameterSet
	 * @param user
	 * @param outputFormat
	 * @param configs
	 * @throws ReportExecutorException
	 */
	void doVetoReportExecution(Report report, ParameterSet parameterSet,
			User user, String outputFormat, ReportExecutionConfig[] configs) throws ReportExecutorException;

}
