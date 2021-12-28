package net.datenwerke.gxtdto.client.i18n.remotemessages;

import com.google.gwt.inject.client.AbstractGinModule;

public class RemoteMessageUIModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(RemoteMessageUiStartup.class).asEagerSingleton();
   }

}
