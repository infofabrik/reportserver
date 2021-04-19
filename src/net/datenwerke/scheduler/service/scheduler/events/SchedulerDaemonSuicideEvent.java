package net.datenwerke.scheduler.service.scheduler.events;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class SchedulerDaemonSuicideEvent extends DwLoggedEvent {

	public SchedulerDaemonSuicideEvent(Object... properties){
		super(properties);
	}
	
	@Override
	public String getLoggedAction() {
		return "SCHEDULER_DAEMON_SUICIDE_ATTEMPT";
	}

}
