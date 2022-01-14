package net.datenwerke.gf.service.maintenance;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.utils.daemon.DwDaemonServiceImpl;
import net.datenwerke.rs.utils.daemon.DwDaemonWatchdog;

public class MaintenanceServiceImpl extends DwDaemonServiceImpl<MaintenanceDaemon> implements MaintenanceService {

   @Inject
   public MaintenanceServiceImpl(Provider<MaintenanceDaemon> daemonProvider,
         Provider<DwDaemonWatchdog> watchdogProvider) {
      super(daemonProvider, watchdogProvider);
   }

}
