package net.datenwerke.rs.base.client.parameters;

import net.datenwerke.rs.base.client.parameters.datasource.DatasourceParameterUiService;
import net.datenwerke.rs.base.client.parameters.datasource.DatasourceParameterUiServiceImpl;

import com.google.gwt.inject.client.AbstractGinModule;

public class RSBasicParametersUIModule extends AbstractGinModule {

	@Override
	protected void configure() {
		/* bind service */
		bind(RSParametersUIStartup.class).asEagerSingleton();
		bind(DatasourceParameterUiService.class).to(DatasourceParameterUiServiceImpl.class).asEagerSingleton();
	}

}
