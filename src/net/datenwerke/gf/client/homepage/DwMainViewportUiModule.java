package net.datenwerke.gf.client.homepage;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.homepage.ui.HeaderPanel;

public class DwMainViewportUiModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(DwMainViewportUiService.class).to(DwMainViewportUiServiceImpl.class);

      bind(HeaderPanel.class).in(Singleton.class);
   }

}
