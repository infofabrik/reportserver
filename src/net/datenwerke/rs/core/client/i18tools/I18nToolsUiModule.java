package net.datenwerke.rs.core.client.i18tools;

import net.datenwerke.gxtdto.client.i18n.I18nToolsUIService;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class I18nToolsUiModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(I18nToolsUIService.class).to(I18nToolsUiServiceImpl.class).in(Singleton.class);
		
		bind(I18nToolsUiStartup.class).asEagerSingleton();
	}

}
