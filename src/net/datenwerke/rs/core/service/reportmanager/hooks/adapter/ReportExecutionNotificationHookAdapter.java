package net.datenwerke.rs.core.service.reportmanager.hooks.adapter;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportExecutionNotificationHook;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.hookservices.HookAdapterProcessor")
public class ReportExecutionNotificationHookAdapter implements ReportExecutionNotificationHook {

	@Override
	public void notifyOfReportExecution(Report report, ParameterSet parameterSet, User user, String outputFormat, net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig[] configs)  {
	}


	@Override
	public void notifyOfReportsSuccessfulExecution(CompiledReport compiledReport, Report report, ParameterSet parameterSet, User user, String outputFormat, net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig[] configs)  {
	}


	@Override
	public void notifyOfReportsUnsuccessfulExecution(ReportExecutorException e, Report report, ParameterSet parameterSet, User user, String outputFormat, net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig[] configs)  {
	}


	@Override
	public void doVetoReportExecution(Report report, ParameterSet parameterSet, User user, String outputFormat, net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig[] configs)  {
	}



}
