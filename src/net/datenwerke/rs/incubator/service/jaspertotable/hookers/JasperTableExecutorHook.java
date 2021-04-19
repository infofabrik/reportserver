package net.datenwerke.rs.incubator.service.jaspertotable.hookers;

import java.io.OutputStream;

import net.datenwerke.rs.base.service.reportengines.jasper.JasperReportEngine;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.ReportEngine;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.engine.hooks.ReportEngineTakeOverExecutionHook;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.incubator.service.jaspertotable.JasperToTableService;
import net.datenwerke.security.service.usermanager.entities.User;

import com.google.inject.Inject;

public class JasperTableExecutorHook implements
		ReportEngineTakeOverExecutionHook {

	private final ReportExecutorService reportExecutorService;
	private final JasperToTableService jasperTableService;
	
	@Inject
	public JasperTableExecutorHook(
		ReportExecutorService reportExecutorService,
		JasperToTableService jasperTableService
		){
		
		/* store objects */
		this.reportExecutorService = reportExecutorService;
		this.jasperTableService = jasperTableService;
	}
	
	@Override
	public boolean takesOver(ReportEngine engine, Report report,
			ParameterSet additionalParameters, User user, String outputFormat,
			ReportExecutionConfig[] configs) {
		return JasperReportEngine.class.equals(engine.getClass()) && outputFormat.startsWith("TABLE_");
	}

	@Override
	public CompiledReport executeReport(ReportEngine engine, OutputStream os, Report report,
			ParameterSet additionalParameters, User user, String outputFormat,
			ReportExecutionConfig[] configs) throws ReportExecutorException {
		JasperReport jReport = (JasperReport) report;
		
		TableReport tReport = jasperTableService.transformToTableReport(jReport);
		
		return reportExecutorService.execute(os, tReport, additionalParameters, user, outputFormat.substring(6), configs);
	}
	
	@Override
	public CompiledReport executeReportDry(ReportEngine engine,
			Report report, ParameterSet additionalParameters,
			User user, String outputFormat, ReportExecutionConfig[] configs)
			throws ReportExecutorException {
		return reportExecutorService.executeDry(report, additionalParameters, user, outputFormat.substring(6), configs);
	}

	@Override
	public boolean supportsStreaming(ReportEngine reportEngine, Report report,
			ParameterSet parameterSet, User user, String outputFormat,
			ReportExecutionConfig[] configs) {

		return false;
	}

}
