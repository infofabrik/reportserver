package net.datenwerke.rs.birt.service.reportengine.sandbox;

import java.util.concurrent.Callable;
import java.util.logging.Level;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;

public class BirtEngineLoaderEnvironment implements
		Callable<Object> {

	@Override
	public Object call() throws Exception {
		EngineConfig config = new EngineConfig();

		Platform.startup(config); 
		IReportEngineFactory reportEngineFactory = (IReportEngineFactory) Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
		IReportEngine reportEngine = reportEngineFactory.createReportEngine(config);
		reportEngine.changeLogLevel(Level.WARNING);

		return reportEngine;
	}

}
