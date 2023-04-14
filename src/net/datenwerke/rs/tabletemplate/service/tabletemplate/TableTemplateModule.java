package net.datenwerke.rs.tabletemplate.service.tabletemplate;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;
import net.datenwerke.rs.tabletemplate.service.engines.jxls.JxlsTemplateModule;
import net.datenwerke.rs.tabletemplate.service.engines.velocity.VelocityTemplateModule;
import net.datenwerke.rs.tabletemplate.service.engines.xdoc.XdocTemplateModule;
import net.datenwerke.rs.tabletemplate.service.engines.xsl.XslTemplateModule;

public class TableTemplateModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
      install(new JxlsTemplateModule());
      install(new VelocityTemplateModule());
      install(new XdocTemplateModule());
      install(new XslTemplateModule());
      
      /* startup */
      bind(TableTemplateStartup.class).asEagerSingleton();
   }

}
