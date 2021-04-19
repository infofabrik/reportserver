package net.datenwerke.rs.incubator.client.outputformatauth;

import com.google.gwt.inject.client.AbstractGinModule;

public class OutputFormatAuthUiModule extends AbstractGinModule {
	
	@Override
	protected void configure() {
		bind(OutputFormatAuthUiStartup.class).asEagerSingleton();
	}

}
