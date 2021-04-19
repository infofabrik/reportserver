package net.datenwerke.rs.tsreportarea.service.tsreportarea.vfs;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;


public class TsFavoritesVFSModule extends AbstractReportServerModule {

	@Override
	protected void configure() {
		bind(TsFavoritesVFSStartup.class).asEagerSingleton();
	}

}
