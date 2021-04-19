package net.datenwerke.rs.eximport.service.eximport.im.http;

import com.google.inject.AbstractModule;
import com.google.inject.servlet.ServletScopes;


public class HttpImportModule extends AbstractModule {

	@Override
	protected void configure() {
		/* bind http config to session */
		bind(HttpImportService.class).to(HttpImportServiceImpl.class).in(ServletScopes.SESSION);
		
	}

}
