package net.datenwerke.rs.core.service.genrights;

import net.datenwerke.gf.service.genrights.GenRightsAdministrationModule;
import net.datenwerke.rs.core.service.genrights.access.GenRightsAccessRsModule;
import net.datenwerke.rs.core.service.genrights.datasinks.GenRightsDatasinkManagerModule;
import net.datenwerke.rs.core.service.genrights.datasources.GenRightsDatasourceManagerModule;
import net.datenwerke.rs.core.service.genrights.remoteservers.GenRightsRemoteServerManagerModule;
import net.datenwerke.rs.core.service.genrights.reportmanager.GenRightsReportManagerModule;
import net.datenwerke.rs.core.service.genrights.transport.GenRightsTransportManagerModule;
import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class GenRightsModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
      install(new GenRightsAdministrationModule());
      install(new GenRightsDatasourceManagerModule());
      install(new GenRightsDatasinkManagerModule());
      install(new GenRightsRemoteServerManagerModule());
      install(new GenRightsTransportManagerModule());
      install(new GenRightsReportManagerModule());
      install(new GenRightsAccessRsModule());
   }

}
