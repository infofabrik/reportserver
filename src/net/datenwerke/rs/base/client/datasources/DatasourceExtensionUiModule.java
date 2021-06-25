package net.datenwerke.rs.base.client.datasources;

import javax.inject.Singleton;

import com.google.gwt.inject.client.AbstractGinModule;

public class DatasourceExtensionUiModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(BaseDatasourceUiService.class).to(BaseDatasourceUiServiceImpl.class).in(Singleton.class);

      /* startup */
      bind(DatasourceExtensionUiStartup.class).asEagerSingleton();
   }

}
