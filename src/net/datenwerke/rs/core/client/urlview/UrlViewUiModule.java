package net.datenwerke.rs.core.client.urlview;

import com.google.gwt.inject.client.AbstractGinModule;

public class UrlViewUiModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(UrlViewUiStartup.class).asEagerSingleton();
	}

}
