package net.datenwerke.rs.incubator.client.reportmetadata;

import com.google.gwt.inject.client.AbstractGinModule;

public class ReportMetadataUIModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(ReportMetadataUIStartup.class).asEagerSingleton();
	}

}
