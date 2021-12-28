package net.datenwerke.rs.globalconstants.client.globalconstants;

import com.google.gwt.inject.client.AbstractGinModule;

/**
 * 
 *
 */
public class GlobalConstantsUIModule extends AbstractGinModule {

   @Override
   protected void configure() {
      bind(GlobalConstantsUIStartup.class).asEagerSingleton();
   }

}
