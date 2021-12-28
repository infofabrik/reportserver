package net.datenwerke.rs.birt.service.reportengine.sandbox;

import java.sql.Connection;

import org.eclipse.birt.report.engine.api.IReportEngine;

import net.datenwerke.rs.birt.service.reportengine.output.generator.BirtOutputGenerator;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;

public interface BirtEngineEnvironmentFactory {
	public BirtEngineEnvironment create(
			IReportEngine reportEngine,
			byte[] reportBytes,
			ParameterSet parameters,
			Connection connection,
			String outputFormat,
			BirtOutputGenerator outputGenerator,
			ReportExecutionConfig[] configs);
}
