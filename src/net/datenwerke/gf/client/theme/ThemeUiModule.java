package net.datenwerke.gf.client.theme;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class ThemeUiModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(ThemeUiService.class).to(ThemeUiServiceImpl.class).in(Singleton.class);
		bind(ThemeUiStartup.class).asEagerSingleton();
	}

}
