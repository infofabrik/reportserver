package net.datenwerke.rs.incubator.client.managerhelpersearch;

import com.google.gwt.inject.client.AbstractGinModule;

public class ManagerHelperSearchUiModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(ManagerHelperSearchUiStartup.class).asEagerSingleton();
	}

}
