package net.datenwerke.usermanager.ext.client.properties;

import com.google.gwt.inject.client.AbstractGinModule;

public class UserManagerPropertiesUiModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(UserManagerPropertiesUiStartup.class).asEagerSingleton();
	}

}
