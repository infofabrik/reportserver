package net.datenwerke.gf.client.administration;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * 
 *
 */
public class AdministrationUIModule extends AbstractGinModule {

   public static final String ADMIN_PANEL_ID = "administrationModuleMainPanel";

   @Override
   protected void configure() {
      /* bind service */
      bind(AdministrationUIService.class).to(AdministrationUIServiceImpl.class).in(Singleton.class);

      /* bind startup */
      bind(AdministrationUIStartup.class).asEagerSingleton();
   }

}
