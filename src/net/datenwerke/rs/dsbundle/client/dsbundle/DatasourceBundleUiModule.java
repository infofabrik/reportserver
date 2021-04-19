package net.datenwerke.rs.dsbundle.client.dsbundle;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class DatasourceBundleUiModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DatasourceBundleUiService.class).to(DatasourceBundleUiServiceImpl.class).in(Singleton.class);
		bind(DatasourceBundleUiStartup.class).asEagerSingleton();
	}

}
