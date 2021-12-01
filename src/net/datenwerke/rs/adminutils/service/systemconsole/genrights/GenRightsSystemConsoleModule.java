package net.datenwerke.rs.adminutils.service.systemconsole.genrights;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;


public class GenRightsSystemConsoleModule extends AbstractReportServerModule {

	@Override
	protected void configure() {
		bind(GenRightsSystemConsoleStartup.class).asEagerSingleton();
	}

}
