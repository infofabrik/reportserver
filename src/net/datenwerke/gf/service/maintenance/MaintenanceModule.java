package net.datenwerke.gf.service.maintenance;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import net.datenwerke.gf.service.maintenance.hooks.MaintenanceTask;

/**
 * A module providing a maintenance thread which periodically runs a set of
 * maintenance tasks ({@link MaintenanceTask}).
 * 
 * The configuration is performed in file "main/main.cf".
 * 
 * @see MaintenanceTask
 *
 */
public class MaintenanceModule extends AbstractModule {

   public static final String CONFIG_FILE = "main/main.cf";

   @Override
   protected void configure() {
      bind(MaintenanceService.class).to(MaintenanceServiceImpl.class).in(Singleton.class);
      bind(MaintenanceStartup.class).asEagerSingleton();
   }

}
