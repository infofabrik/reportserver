package net.datenwerke.rs.terminal.service.terminal.objresolver;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class ObjectResolverModule extends AbstractReportServerModule {

	@Override
	protected void configure() {
		bind(ObjectResolverStartup.class).asEagerSingleton();
	}

}
