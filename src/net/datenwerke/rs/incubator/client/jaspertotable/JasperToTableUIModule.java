package net.datenwerke.rs.incubator.client.jaspertotable;

import com.google.gwt.inject.client.AbstractGinModule;

public class JasperToTableUIModule extends AbstractGinModule {

	public static final String PROPERTY_NAME = "jaspertotable:config";
	
	@Override
	protected void configure() {
		bind(JasperToTableUIStartup.class).asEagerSingleton();
	}

}
