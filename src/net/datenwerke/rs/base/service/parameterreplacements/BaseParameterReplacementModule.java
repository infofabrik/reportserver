package net.datenwerke.rs.base.service.parameterreplacements;

import com.google.inject.AbstractModule;

public class BaseParameterReplacementModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(BaseParameterReplacementStartup.class).asEagerSingleton();
   }

}
