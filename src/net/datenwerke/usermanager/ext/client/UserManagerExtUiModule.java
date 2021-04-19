package net.datenwerke.usermanager.ext.client;

import com.google.gwt.inject.client.AbstractGinModule;

import net.datenwerke.usermanager.ext.client.eximport.ex.UserManagerExportUIModule;
import net.datenwerke.usermanager.ext.client.properties.UserManagerPropertiesUiModule;


public class UserManagerExtUiModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(UserManagerExtUiStartup.class).asEagerSingleton();
		
		/* sub modules */
		install(new UserManagerExportUIModule());
		install(new UserManagerPropertiesUiModule());
	}

}
