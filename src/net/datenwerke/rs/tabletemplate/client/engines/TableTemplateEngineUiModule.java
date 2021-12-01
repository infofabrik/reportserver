package net.datenwerke.rs.tabletemplate.client.engines;

import com.google.gwt.inject.client.AbstractGinModule;

import net.datenwerke.rs.tabletemplate.client.engines.jxls.JXlsTemplateUIModule;
import net.datenwerke.rs.tabletemplate.client.engines.jxls2.JXls2TemplateUIModule;
import net.datenwerke.rs.tabletemplate.client.engines.velocity.VelocityTemplateUIModule;
import net.datenwerke.rs.tabletemplate.client.engines.xdoc.XdocTemplateUIModule;
import net.datenwerke.rs.tabletemplate.client.engines.xsl.XslTemplateUIModule;

public class TableTemplateEngineUiModule extends AbstractGinModule {

	@Override
	protected void configure() {
		install(new JXlsTemplateUIModule());
		install(new JXls2TemplateUIModule());
		install(new XdocTemplateUIModule());
		install(new XslTemplateUIModule());
		install(new VelocityTemplateUIModule());
	}

}
