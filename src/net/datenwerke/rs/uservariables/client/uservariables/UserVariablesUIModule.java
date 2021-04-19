package net.datenwerke.rs.uservariables.client.uservariables;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class UserVariablesUIModule extends AbstractGinModule {

	@Override
	protected void configure() {
		/* bind service */
		bind(UserVariablesUIService.class).to(UserVariablesUIServiceImpl.class).in(Singleton.class);
		
		/* startup */
		bind(UserVariablesUIStartup.class).asEagerSingleton();
	}

}
