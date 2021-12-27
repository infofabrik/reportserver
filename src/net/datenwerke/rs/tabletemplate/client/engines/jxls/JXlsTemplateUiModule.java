package net.datenwerke.rs.tabletemplate.client.engines.jxls;

import com.google.gwt.inject.client.AbstractGinModule;

public class JXlsTemplateUiModule extends AbstractGinModule {

   public static final String TEMPLATE_TYPE = "jxls:template";

   @Override
   protected void configure() {
      bind(JXlsTemplateUiStartup.class).asEagerSingleton();
   }

}