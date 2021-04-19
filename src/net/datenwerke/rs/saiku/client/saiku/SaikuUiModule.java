package net.datenwerke.rs.saiku.client.saiku;

import javax.inject.Singleton;

import com.google.gwt.inject.client.AbstractGinModule;

public class SaikuUiModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(SaikuUiService.class).to(SaikuUiServiceImpl.class).in(Singleton.class);
		
		bind(SaikuUiStartup.class).asEagerSingleton();
	}


}
