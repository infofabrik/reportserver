package net.datenwerke.gf.service.lifecycle.events;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class InvalidateSessionEvent extends DwLoggedEvent {

	@Override
	public String getLoggedAction() {
		return "INVALIDATE_SESSION";
	}

}
