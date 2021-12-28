package net.datenwerke.rs.globalconstants.service.globalconstants.genrights;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class GenRightsGlobalConstantsManagerModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
      bind(GenRightsGlobalConstantsManagerStartup.class).asEagerSingleton();
   }

}
