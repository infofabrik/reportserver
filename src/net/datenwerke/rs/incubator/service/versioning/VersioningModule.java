package net.datenwerke.rs.incubator.service.versioning;



import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;
import net.datenwerke.rs.incubator.service.versioning.entities.Revision;

import com.google.inject.Scopes;

public class VersioningModule extends AbstractReportServerModule{

	@Override
	protected void configure() {
		bind(VersioningService.class).to(VersioningServiceImpl.class).in(Scopes.SINGLETON);
		bind(VersioningStartup.class).asEagerSingleton();
		requestStaticInjection(Revision.class);
	}

}
