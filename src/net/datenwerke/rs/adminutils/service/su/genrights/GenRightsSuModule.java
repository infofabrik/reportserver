package net.datenwerke.rs.adminutils.service.su.genrights;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;


public class GenRightsSuModule extends AbstractReportServerModule {

	@Override
	protected void configure() {
		bind(GenRightsSuStartup.class).asEagerSingleton();
	}

}
