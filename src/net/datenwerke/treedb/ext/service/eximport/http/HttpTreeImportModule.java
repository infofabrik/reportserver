package net.datenwerke.treedb.ext.service.eximport.http;

import javax.inject.Singleton;

import com.google.inject.AbstractModule;

public class HttpTreeImportModule extends AbstractModule {

	@Override
	protected void configure() {
		/* bind http config to session */
		bind(HttpTreeImportService.class).to(HttpTreeImportServiceImpl.class).in(Singleton.class);
		
	}

}
