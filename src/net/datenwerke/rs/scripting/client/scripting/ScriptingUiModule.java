package net.datenwerke.rs.scripting.client.scripting;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class ScriptingUiModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(ScriptingUiService.class).to(ScriptingUiServiceImpl.class).in(Singleton.class);
      bind(ScriptingUiStartup.class).asEagerSingleton();
   }

}
