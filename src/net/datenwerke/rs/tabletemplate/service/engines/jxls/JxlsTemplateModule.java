package net.datenwerke.rs.tabletemplate.service.engines.jxls;

import com.google.inject.AbstractModule;

public class JxlsTemplateModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(JxlsTemplateStartup.class).asEagerSingleton();
   }


}
