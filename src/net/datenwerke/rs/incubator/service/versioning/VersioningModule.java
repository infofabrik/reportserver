package net.datenwerke.rs.incubator.service.versioning;

import com.google.inject.Scopes;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;
import net.datenwerke.rs.incubator.service.versioning.entities.Revision;

public class VersioningModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
      bind(VersioningService.class).to(VersioningServiceImpl.class).in(Scopes.SINGLETON);
      bind(VersioningStartup.class).asEagerSingleton();
      requestStaticInjection(Revision.class);
   }

}
