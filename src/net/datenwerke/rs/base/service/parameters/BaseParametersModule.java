package net.datenwerke.rs.base.service.parameters;

import com.google.inject.AbstractModule;

import net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterModule;

public class BaseParametersModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(BaseParametersStartup.class).asEagerSingleton();

      /* submodules */
      install(new DatasourceParameterModule());
   }

}
