package net.datenwerke.rs.core.service.genrights;

import net.datenwerke.gf.service.genrights.GenRightsAdministrationModule;
import net.datenwerke.rs.core.service.genrights.access.GenRightsAccessRsModule;
import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class GenRightsModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
      install(new GenRightsAdministrationModule());
      install(new GenRightsAccessRsModule());
   }

}
