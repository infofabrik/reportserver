package net.datenwerke.rs.adminutils.client.suuser;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class SuUserUIModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(SuUserUiService.class).to(SuUserUiServiceImpl.class).in(Singleton.class);
      bind(SuUserUIStartup.class).asEagerSingleton();
   }

}
