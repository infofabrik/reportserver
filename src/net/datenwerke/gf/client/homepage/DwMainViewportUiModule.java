package net.datenwerke.gf.client.homepage;

import net.datenwerke.gf.client.homepage.ui.HeaderPanel;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class DwMainViewportUiModule extends AbstractGinModule{

	@Override
	protected void configure() {
		bind(DwMainViewportUiService.class).to(DwMainViewportUiServiceImpl.class);
		
		bind(HeaderPanel.class).in(Singleton.class);
	}

}
