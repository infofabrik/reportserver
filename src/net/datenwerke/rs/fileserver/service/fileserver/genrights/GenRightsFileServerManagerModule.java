package net.datenwerke.rs.fileserver.service.fileserver.genrights;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;


public class GenRightsFileServerManagerModule extends AbstractReportServerModule {

	@Override
	protected void configure() {
		bind(GenRightsFileServerManagerStartup.class).asEagerSingleton();
	}

}
