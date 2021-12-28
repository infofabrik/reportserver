package net.datenwerke.treedb.ext.service;

import com.google.inject.AbstractModule;

import net.datenwerke.treedb.ext.service.eximport.http.HttpTreeImportModule;

public class TreeDbExtModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new HttpTreeImportModule());
	}

}
