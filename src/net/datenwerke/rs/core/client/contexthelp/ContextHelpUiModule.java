package net.datenwerke.rs.core.client.contexthelp;

import com.google.gwt.inject.client.AbstractGinModule;

public class ContextHelpUiModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(ContextHelpUiStartup.class).asEagerSingleton();
      bind(ContextHelpUiService.class).to(ContextHelpUiServiceImpl.class);
   }

}
