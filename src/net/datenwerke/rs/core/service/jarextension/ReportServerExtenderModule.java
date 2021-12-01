package net.datenwerke.rs.core.service.jarextension;

import com.google.inject.AbstractModule;

public class ReportServerExtenderModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ReportServerExtenderStartup.class).asEagerSingleton();
	}

}
