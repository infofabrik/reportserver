package net.datenwerke.rs.adminutils.client.systemconsole.generalinfo;

import com.google.gwt.inject.client.AbstractGinModule;

public class GeneralInfoUiModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(GeneralInfoUiStartup.class).asEagerSingleton();
	}

}
