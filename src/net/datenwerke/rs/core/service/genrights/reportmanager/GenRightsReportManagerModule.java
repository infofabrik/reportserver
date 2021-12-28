package net.datenwerke.rs.core.service.genrights.reportmanager;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class GenRightsReportManagerModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
      bind(GenRightsReportManagerStartup.class).asEagerSingleton();
   }

}
