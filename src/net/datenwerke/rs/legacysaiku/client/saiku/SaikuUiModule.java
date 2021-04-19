package net.datenwerke.rs.legacysaiku.client.saiku;

import com.google.gwt.inject.client.AbstractGinModule;

public class SaikuUiModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(SaikuUiStartup.class).asEagerSingleton();
	}


}
