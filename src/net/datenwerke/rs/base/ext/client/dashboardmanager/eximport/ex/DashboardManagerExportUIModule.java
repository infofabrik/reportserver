package net.datenwerke.rs.base.ext.client.dashboardmanager.eximport.ex;

import com.google.gwt.inject.client.AbstractGinModule;

public class DashboardManagerExportUIModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(DashboardManagerExportUIStartup.class).asEagerSingleton();
	}

}
