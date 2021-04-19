package net.datenwerke.security.service.authenticator.events;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class FailedLoginEvent extends DwLoggedEvent{

	public FailedLoginEvent(Object... properties){
		super(properties);
	}
	
	@Override
	public String getLoggedAction() {
		return "FAILED_LOGIN";
	}
	
}