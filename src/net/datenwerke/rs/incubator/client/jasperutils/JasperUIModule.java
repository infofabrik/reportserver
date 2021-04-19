package net.datenwerke.rs.incubator.client.jasperutils;

import com.google.gwt.inject.client.AbstractGinModule;

public class JasperUIModule extends AbstractGinModule {

	@Override
	protected void configure() {
		/* startup */
		bind(JasperUIStartup.class).asEagerSingleton();
	}

}
