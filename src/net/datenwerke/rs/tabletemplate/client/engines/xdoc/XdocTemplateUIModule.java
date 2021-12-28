package net.datenwerke.rs.tabletemplate.client.engines.xdoc;

import com.google.gwt.inject.client.AbstractGinModule;

public class XdocTemplateUIModule extends AbstractGinModule {

   public static final String TEMPLATE_TYPE = "datenwerke:template:xdoc";

   @Override
   protected void configure() {
      bind(XdocTemplateUIStartup.class).asEagerSingleton();
   }

}
