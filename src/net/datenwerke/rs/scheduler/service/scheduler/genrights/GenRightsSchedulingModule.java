package net.datenwerke.rs.scheduler.service.scheduler.genrights;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;


public class GenRightsSchedulingModule extends AbstractReportServerModule {

	@Override
	protected void configure() {
		bind(GenRightsSchedulingStartup.class).asEagerSingleton();
	}

}
