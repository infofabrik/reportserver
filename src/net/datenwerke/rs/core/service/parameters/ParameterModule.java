package net.datenwerke.rs.core.service.parameters;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class ParameterModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
      bind(ParameterHelperService.class).to(ParameterHelperServiceImpl.class);
      
      bind(ParameterStartup.class).asEagerSingleton();
   }

}
