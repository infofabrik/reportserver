package net.datenwerke.rs.core.service.genrights.datasinks;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;


public class GenRightsDatasinkManagerModule extends AbstractReportServerModule {

	@Override
	protected void configure() {
		bind(GenRightsDatasinkManagerStartup.class).asEagerSingleton();
	}

}
