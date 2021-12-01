package net.datenwerke.rs.core.service.reportmanager.engine.hooks;

import java.io.OutputStream;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.ReportEngine;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.security.service.usermanager.entities.User;

@HookConfig
public interface ReportEngineTakeOverExecutionHook extends Hook {

	/**
	 * return true in order to bypass the report engine and perform manual execution of the report
	 */
	boolean takesOver(ReportEngine engine, Report report, ParameterSet additionalParameters,
			User user, String outputFormat, ReportExecutionConfig[] configs);

	/**
	 * Execute the report. Note that if os is not null then the actual report data needs to be
	 * written to the output stream.
	 */
	CompiledReport executeReport(ReportEngine engine, OutputStream os, Report report,
			ParameterSet additionalParameters, User user, String outputFormat,
			ReportExecutionConfig[] configs) throws ReportExecutorException, ExpectedException;
	
	/**
	 * Return an empty CompiledReport object to specify mime type and extension.
	 */
	CompiledReport executeReportDry(ReportEngine engine, Report report,
			ParameterSet additionalParameters, User user, String outputFormat,
			ReportExecutionConfig[] configs) throws ReportExecutorException, ExpectedException;

	boolean supportsStreaming(ReportEngine reportEngine,
			Report report, ParameterSet parameterSet, User user,
			String outputFormat, ReportExecutionConfig[] configs);

}
