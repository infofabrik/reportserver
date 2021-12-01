package net.datenwerke.rs.tabletemplate.client.engines.xsl;

import com.google.gwt.inject.client.AbstractGinModule;

public class XslTemplateUIModule extends AbstractGinModule {

	public static final String TEMPLATE_TYPE = "datenwerke:template:xsl";
	
	@Override
	protected void configure() {
		bind(XslTemplateUIStartup.class).asEagerSingleton();
	}

}
