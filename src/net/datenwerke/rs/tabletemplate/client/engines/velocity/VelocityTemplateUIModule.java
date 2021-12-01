package net.datenwerke.rs.tabletemplate.client.engines.velocity;

import com.google.gwt.inject.client.AbstractGinModule;

public class VelocityTemplateUIModule extends AbstractGinModule {

	public static final String TEMPLATE_TYPE = "datenwerke:template:velocity";
	
	@Override
	protected void configure() {
		bind(VelocityTemplateUIStartup.class).asEagerSingleton();
	}

}
