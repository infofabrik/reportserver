package net.datenwerke.rs.teamspace.service.teamspace;

import com.google.inject.Singleton;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;

/**
 * 
 *
 */
public class TeamSpaceModule extends AbstractReportServerModule {

	@Override
	protected void configure() {
		/* bind service */
		bind(TeamSpaceService.class).to(TeamSpaceServiceImpl.class).in(Singleton.class);
		
		/* startup */
		bind(TeamSpaceStartup.class).asEagerSingleton();
		
		requestStaticInjection(TeamSpace.class);
	}

}
