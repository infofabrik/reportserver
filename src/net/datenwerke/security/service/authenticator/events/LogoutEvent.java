package net.datenwerke.security.service.authenticator.events;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class LogoutEvent extends DwLoggedEvent{

	public LogoutEvent(Object... properties){
		super(properties);
	}
	
	@Override
	public String getLoggedAction() {
		return "LOGOUT";
	}
	
}