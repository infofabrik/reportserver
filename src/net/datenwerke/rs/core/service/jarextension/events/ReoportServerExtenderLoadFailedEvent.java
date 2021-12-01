package net.datenwerke.rs.core.service.jarextension.events;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class ReoportServerExtenderLoadFailedEvent extends DwLoggedEvent {

	public ReoportServerExtenderLoadFailedEvent(String extender, String error){
		super("extender", extender, "error", error);
	}
	
	public ReoportServerExtenderLoadFailedEvent(String error) {
		super("error", error);
	}

	@Override
	public String getLoggedAction() {
		return "EXTENDER_STARTUP_FAILED";
	}

}
