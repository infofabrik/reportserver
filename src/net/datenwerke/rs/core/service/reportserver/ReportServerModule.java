package net.datenwerke.rs.core.service.reportserver;

import com.google.inject.Singleton;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;
import net.datenwerke.rs.utils.properties.ApplicationPropertiesService;
import net.datenwerke.rs.utils.properties.ApplicationPropertiesServiceImpl;

public class ReportServerModule extends AbstractReportServerModule {

   public final static String USER_PROPERTY_ACCOUNT_INHIBITED = "accountInhibited";
   public final static String USER_PROPERTY_ACCOUNT_EXPIRATION_DATE = "accountExpirationDate";

   @Override
   protected void configure() {
      bind(ReportServerService.class).to(ReportServerServiceImpl.class).in(Singleton.class);
      bind(ApplicationPropertiesService.class).to(ApplicationPropertiesServiceImpl.class).in(Singleton.class);

      bind(ReportServerServiceStartup.class).asEagerSingleton();
   }

}
