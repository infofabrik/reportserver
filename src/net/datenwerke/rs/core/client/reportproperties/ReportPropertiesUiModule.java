package net.datenwerke.rs.core.client.reportproperties;

import com.google.gwt.inject.client.AbstractGinModule;

public class ReportPropertiesUiModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(ReportPropertiesUiStartup.class).asEagerSingleton();
	}

}
