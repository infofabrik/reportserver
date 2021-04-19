package net.datenwerke.gf.service.maintenance.event;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class MaintenanceJobStarted extends DwLoggedEvent {

	public MaintenanceJobStarted(Object... properties){
		super(properties);
	}
	
	@Override
	public String getLoggedAction() {
		return "MAINTENANCE_JOB_STARTED";
	}

}
