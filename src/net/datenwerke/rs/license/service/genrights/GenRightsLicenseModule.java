package net.datenwerke.rs.license.service.genrights;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;


public class GenRightsLicenseModule extends AbstractReportServerModule {

	@Override
	protected void configure() {
		bind(GenRightsLicenseStartup.class).asEagerSingleton();
	}

}
