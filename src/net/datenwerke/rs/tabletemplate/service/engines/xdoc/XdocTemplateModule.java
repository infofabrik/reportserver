package net.datenwerke.rs.tabletemplate.service.engines.xdoc;

import com.google.inject.AbstractModule;

public class XdocTemplateModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(XdocTemplateStartup.class).asEagerSingleton();
   }


}
