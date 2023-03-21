package net.datenwerke.rs.adminutils.client.systemconsole.generalinfo;

import com.google.gwt.inject.client.AbstractGinModule;

public class GeneralInfoUiModule extends AbstractGinModule {

   public final static String ENABLED = "enabled";
   public final static String DISABLED_MESSAGE = "disabled_msg";
   
   @Override
   protected void configure() {
      bind(GeneralInfoUiStartup.class).asEagerSingleton();
   }

}
