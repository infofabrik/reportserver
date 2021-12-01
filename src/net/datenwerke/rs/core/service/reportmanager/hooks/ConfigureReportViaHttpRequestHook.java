package net.datenwerke.rs.core.service.reportmanager.hooks;

import javax.servlet.http.HttpServletRequest;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

public interface ConfigureReportViaHttpRequestHook extends Hook {

	void adjustReport(Report report, HttpServletRequest req);

}
