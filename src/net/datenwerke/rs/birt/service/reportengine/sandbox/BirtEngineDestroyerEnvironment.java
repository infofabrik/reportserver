package net.datenwerke.rs.birt.service.reportengine.sandbox;

import java.util.concurrent.Callable;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.core.internal.registry.RegistryProviderFactory;

public class BirtEngineDestroyerEnvironment implements
		Callable<Object> {

	private final IReportEngine reportEngine;
	
	public BirtEngineDestroyerEnvironment(
		IReportEngine reportEngine
		) {
		this.reportEngine = reportEngine;
	}

	@Override
	public Object call() throws Exception {
		reportEngine.destroy();
		Platform.shutdown();
		RegistryProviderFactory.releaseDefault();

		return null;
	}

}
