package net.datenwerke.rs.fileserver.service.fileserver.vfs;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;


public class FileServerVfsModule extends AbstractReportServerModule {

	@Override
	protected void configure() {
		bind(FileServerVfsStartup.class).asEagerSingleton();
	}

}
