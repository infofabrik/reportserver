package net.datenwerke.rs.core.service.genrights.datasources;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;


public class GenRightsDatasourceManagerModule extends AbstractReportServerModule {

	@Override
	protected void configure() {
		bind(GenRightsDatasourceManagerStartup.class).asEagerSingleton();
	}

}
