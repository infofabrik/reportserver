package net.datenwerke.rs.tabletemplate.service.engines.xsl;

import com.google.inject.AbstractModule;

public class XslTemplateModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(XslTemplateStartup.class).asEagerSingleton();
   }


}
