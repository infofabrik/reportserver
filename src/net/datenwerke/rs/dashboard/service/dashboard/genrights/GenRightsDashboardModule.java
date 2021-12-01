package net.datenwerke.rs.dashboard.service.dashboard.genrights;

import com.google.inject.AbstractModule;



public class GenRightsDashboardModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(GenRightsDashboardStartup.class).asEagerSingleton();
	}

}
