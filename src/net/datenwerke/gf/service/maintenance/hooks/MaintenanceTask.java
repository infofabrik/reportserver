package net.datenwerke.gf.service.maintenance.hooks;

import net.datenwerke.gf.service.maintenance.MaintenanceModule;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;

/**
 * Allows to register a maintenance task. Maintenance tasks are regularly
 * executed depending on the configuration specified in
 * {@link MaintenanceModule#CONFIG_FILE}.
 * 
 *
 */
@HookConfig
public interface MaintenanceTask extends Hook {

   /**
    * Callback called by the maintenance thread. The method should preferably not
    * throw exceptions.
    */
   void performMaintenance();

}
