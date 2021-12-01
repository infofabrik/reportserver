package net.datenwerke.rs.eximport.client.eximport.im;

import com.google.gwt.inject.client.AbstractGinModule;

public class ImportUIModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(ImportUIStartup.class).asEagerSingleton();
	}

}
