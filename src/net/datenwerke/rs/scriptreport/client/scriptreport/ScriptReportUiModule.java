package net.datenwerke.rs.scriptreport.client.scriptreport;

import com.google.gwt.inject.client.AbstractGinModule;

public class ScriptReportUiModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(ScriptReportUiStartup.class).asEagerSingleton();
	}

}
