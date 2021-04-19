package net.datenwerke.gf.service.maintenance;

import net.datenwerke.rs.utils.daemon.DwDaemonServiceImpl;
import net.datenwerke.rs.utils.daemon.DwDaemonWatchdog;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class MaintenanceServiceImpl extends DwDaemonServiceImpl<MaintenanceDaemon> implements MaintenanceService  {

	@Inject
	public MaintenanceServiceImpl(
		Provider<MaintenanceDaemon> daemonProvider,
		Provider<DwDaemonWatchdog> watchdogProvider
		){
		super(daemonProvider, watchdogProvider);
	}
	
	
	
}
