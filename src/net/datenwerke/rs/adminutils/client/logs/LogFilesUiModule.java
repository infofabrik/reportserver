package net.datenwerke.rs.adminutils.client.logs;

import com.google.gwt.inject.client.AbstractGinModule;

public class LogFilesUiModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(LogFilesUiStartup.class).asEagerSingleton();
	}

}
