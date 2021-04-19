package net.datenwerke.treedb.ext.service;

import net.datenwerke.treedb.ext.service.eximport.http.HttpTreeImportModule;

import com.google.inject.AbstractModule;

public class TreeDbExtModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new HttpTreeImportModule());
	}

}
