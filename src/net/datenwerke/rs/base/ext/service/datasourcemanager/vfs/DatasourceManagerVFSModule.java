package net.datenwerke.rs.base.ext.service.datasourcemanager.vfs;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class DatasourceManagerVFSModule extends AbstractReportServerModule {

	@Override
	protected void configure() {
		bind(DatasourceManagerVFSStartup.class).asEagerSingleton();
	}

}
