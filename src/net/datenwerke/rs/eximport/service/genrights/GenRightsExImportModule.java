package net.datenwerke.rs.eximport.service.genrights;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;


public class GenRightsExImportModule extends AbstractReportServerModule {

	@Override
	protected void configure() {
		bind(GenRightsExImportStartup.class).asEagerSingleton();
	}

}
