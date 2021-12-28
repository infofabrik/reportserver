package net.datenwerke.rs.base.service.parameters.datasource;

import com.google.inject.Singleton;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class DatasourceParameterModule extends AbstractReportServerModule {

	@Override
	protected void configure() {
		/* service */
		bind(DatasourceParameterService.class).to(DatasourceParameterServiceImpl.class).in(Singleton.class);
		
		/* static injection */
		requestStaticInjection(
			DatasourceParameterDefinition.class
		);
	}

}
