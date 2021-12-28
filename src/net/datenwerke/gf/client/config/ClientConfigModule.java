package net.datenwerke.gf.client.config;

import com.google.gwt.inject.client.AbstractGinModule;

public class ClientConfigModule extends AbstractGinModule {

   public static final String MAIN_CLIENT_CONFIG = "ui/ui.cf";

   public static final String CLIENT_CONFIG_FILE_LOADED = "CLIENT_CONFIG_FILE_LOEADED"; //$NON-NLS-1$

   @Override
   protected void configure() {
      bind(ClientConfigStartup.class).asEagerSingleton();
   }

}
