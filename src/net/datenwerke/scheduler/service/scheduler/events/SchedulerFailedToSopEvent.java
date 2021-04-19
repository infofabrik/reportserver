package net.datenwerke.scheduler.service.scheduler.events;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class SchedulerFailedToSopEvent extends DwLoggedEvent {

	public SchedulerFailedToSopEvent(Object... properties){
		super(properties);
	}
	
	@Override
	public String getLoggedAction() {
		return "SCHEDULER_FAILED_TO_STOP";
	}

}
