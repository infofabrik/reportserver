package net.datenwerke.rs.base.client.parameters;

import com.google.gwt.inject.client.AbstractGinModule;

import net.datenwerke.rs.base.client.parameters.datasource.DatasourceParameterUiService;
import net.datenwerke.rs.base.client.parameters.datasource.DatasourceParameterUiServiceImpl;

public class RSBasicParametersUIModule extends AbstractGinModule {

	@Override
	protected void configure() {
		/* bind service */
		bind(RSParametersUIStartup.class).asEagerSingleton();
		bind(DatasourceParameterUiService.class).to(DatasourceParameterUiServiceImpl.class).asEagerSingleton();
	}

}
