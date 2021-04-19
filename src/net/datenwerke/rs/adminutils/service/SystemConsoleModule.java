package net.datenwerke.rs.adminutils.service;

import com.google.inject.AbstractModule;

import net.datenwerke.rs.adminutils.service.systemconsole.genrights.GenRightsSystemConsoleModule;

public class SystemConsoleModule extends AbstractModule {

	@Override
	protected void configure() {
		
		/* rights */
		install(new GenRightsSystemConsoleModule());
	}

}
