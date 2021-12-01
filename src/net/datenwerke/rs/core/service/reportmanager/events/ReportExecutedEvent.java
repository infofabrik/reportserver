package net.datenwerke.rs.core.service.reportmanager.events;

import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class ReportExecutedEvent extends DwLoggedEvent{

	public ReportExecutedEvent(Object... properties){
		super(properties);
	}
	
	@Override
	public String getLoggedAction() {
		return "REPORT_EXECUTION";
	}
	
}
