package net.datenwerke.rs.tabletemplate.client.engines;

import com.google.gwt.inject.client.AbstractGinModule;

import net.datenwerke.rs.tabletemplate.client.engines.jxls.JXlsTemplateUiModule;
import net.datenwerke.rs.tabletemplate.client.engines.velocity.VelocityTemplateUIModule;
import net.datenwerke.rs.tabletemplate.client.engines.xdoc.XdocTemplateUIModule;
import net.datenwerke.rs.tabletemplate.client.engines.xsl.XslTemplateUIModule;

public class TableTemplateEngineUiModule extends AbstractGinModule {

	@Override
	protected void configure() {
		install(new JXlsTemplateUiModule());
		install(new XdocTemplateUIModule());
		install(new XslTemplateUIModule());
		install(new VelocityTemplateUIModule());
	}

}
