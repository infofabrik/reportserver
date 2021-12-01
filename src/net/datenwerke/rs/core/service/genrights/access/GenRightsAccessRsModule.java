package net.datenwerke.rs.core.service.genrights.access;

import com.google.inject.AbstractModule;



public class GenRightsAccessRsModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(GenRightsAccessRsStartup.class).asEagerSingleton();
	}

}
