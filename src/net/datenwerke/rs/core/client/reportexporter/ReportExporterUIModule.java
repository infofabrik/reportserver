package net.datenwerke.rs.core.client.reportexporter;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporterImpl;

/**
 * 
 *
 */
public class ReportExporterUIModule extends AbstractGinModule {

	@Override
	protected void configure() {
		/* ask for static injection */
		requestStaticInjection(
			ReportExporterImpl.class
		);
		
		/* bind service */
		bind(ReportExporterUIService.class).to(ReportExporterUIServiceImpl.class).in(Singleton.class);
		
		/* configure startup class */
		bind(ReportExporterUIStartup.class).asEagerSingleton();
		
	}

}
