package net.datenwerke.rs.core.server.reportexport.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

public interface ReportExportViaSessionHook extends Hook {

	void adjustReport(Report adjustedReport, ReportExecutionConfig... reportExecutorConfigs);

}
