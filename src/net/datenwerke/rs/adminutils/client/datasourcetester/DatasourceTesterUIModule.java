package net.datenwerke.rs.adminutils.client.datasourcetester;

import com.google.gwt.inject.client.AbstractGinModule;

public class DatasourceTesterUIModule extends AbstractGinModule {

	@Override
	protected void configure() {
		/* startup */
		bind(DatasourceTesterUIStartup.class).asEagerSingleton();
	}

}
