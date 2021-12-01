package net.datenwerke.rs.base.service.parameters;

import net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterModule;

import com.google.inject.AbstractModule;

public class BaseParametersModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(BaseParametersStartup.class).asEagerSingleton();
	
		/* submodules */
		install(new DatasourceParameterModule());
	}

}
