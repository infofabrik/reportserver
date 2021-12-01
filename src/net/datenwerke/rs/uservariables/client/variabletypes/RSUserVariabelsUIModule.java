package net.datenwerke.rs.uservariables.client.variabletypes;

import com.google.gwt.inject.client.AbstractGinModule;

public class RSUserVariabelsUIModule extends AbstractGinModule {

	@Override
	protected void configure() {
		/* startup */
		bind(RSUserVariablesUIStartup.class).asEagerSingleton();
	}

}
