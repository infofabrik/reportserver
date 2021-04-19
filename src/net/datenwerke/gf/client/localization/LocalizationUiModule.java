package net.datenwerke.gf.client.localization;

import com.google.gwt.inject.client.AbstractGinModule;

public class LocalizationUiModule extends AbstractGinModule{

	@Override
	protected void configure() {
		bind(LocalizationUiModuleStartup.class).asEagerSingleton();
	}

}
