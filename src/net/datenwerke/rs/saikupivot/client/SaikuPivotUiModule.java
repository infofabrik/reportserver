package net.datenwerke.rs.saikupivot.client;

import com.google.gwt.inject.client.AbstractGinModule;

public class SaikuPivotUiModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(SaikuPivotUiStartup.class).asEagerSingleton();
	}

}
