package net.datenwerke.rs.tabletemplate.service.engines.velocity;

import com.google.inject.AbstractModule;

public class VelocityTemplateModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(VelocityTemplateStartup.class).asEagerSingleton();
   }


}
