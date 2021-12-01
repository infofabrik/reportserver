package net.datenwerke.rs.core.client.reportexporter.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporter;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public interface VetoReportExporterHook extends Hook {

	boolean doesVetoExporter(ReportExporter exporter, ReportDto report);

	
}
