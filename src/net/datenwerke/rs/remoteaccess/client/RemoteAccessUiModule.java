package net.datenwerke.rs.remoteaccess.client;

import com.google.gwt.inject.client.AbstractGinModule;

public class RemoteAccessUiModule extends AbstractGinModule {

	@Override
	protected void configure() {

		bind(RemoteAccessUiStartup.class).asEagerSingleton();
	}

}
