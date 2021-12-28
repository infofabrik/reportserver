package net.datenwerke.rs.eximport.service;

import com.google.inject.AbstractModule;

import net.datenwerke.rs.eximport.service.eximport.ex.http.HttpExportModule;
import net.datenwerke.rs.eximport.service.eximport.im.http.HttpImportModule;
import net.datenwerke.rs.eximport.service.genrights.GenRightsExImportModule;

public class RsExImportModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(RsExImportStartup.class).asEagerSingleton();
		
		/* sub modules */
		install(new HttpImportModule());
		install(new HttpExportModule());
		
		/* submodule */
		install(new GenRightsExImportModule());
	}

}
