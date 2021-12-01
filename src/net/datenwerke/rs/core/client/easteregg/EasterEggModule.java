package net.datenwerke.rs.core.client.easteregg;

import com.google.gwt.inject.client.AbstractGinModule;

public class EasterEggModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(EasterEggStartup.class).asEagerSingleton();
		
	}

}
