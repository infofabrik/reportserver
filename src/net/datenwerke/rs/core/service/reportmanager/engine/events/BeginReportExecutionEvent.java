package net.datenwerke.rs.core.service.reportmanager.engine.events;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.security.service.eventlogger.DwLoggedEvent;

public class BeginReportExecutionEvent extends DwLoggedEvent {

	public BeginReportExecutionEvent(Report report, String uuid) {
		super("report_id", null != report.getId() ? report.getId() : report.getOldTransientId(), "uuid", uuid);
	}

	@Override
	public String getLoggedAction() {
		return "BEGIN_REPORT_EXECUTION";
	}

}
