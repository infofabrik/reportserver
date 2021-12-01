package net.datenwerke.rs.uservariables.service.genrights;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;


public class ClientUserVariableModule extends AbstractReportServerModule {

	@Override
	protected void configure() {
		bind(ClientUserVariableStartup.class).asEagerSingleton();
	}

}
