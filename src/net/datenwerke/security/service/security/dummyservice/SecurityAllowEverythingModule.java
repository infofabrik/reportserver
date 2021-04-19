package net.datenwerke.security.service.security.dummyservice;

import net.datenwerke.security.service.security.SecurityService;

import com.google.inject.AbstractModule;

public class SecurityAllowEverythingModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(SecurityService.class).to(SecurityAllowEverythingService.class);
	}

}
