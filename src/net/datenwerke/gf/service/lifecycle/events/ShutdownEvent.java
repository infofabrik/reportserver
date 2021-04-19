package net.datenwerke.gf.service.lifecycle.events;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class ShutdownEvent extends DwLoggedEvent {

	public ShutdownEvent(Object... properties){
		super(properties);
	}
	
	@Override
	public String getLoggedAction() {
		return "REPORTSERVER_SHUTDOWN";
	}

}
