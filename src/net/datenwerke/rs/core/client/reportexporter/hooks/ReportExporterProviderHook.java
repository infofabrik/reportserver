package net.datenwerke.rs.core.client.reportexporter.hooks;

import java.util.List;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporter;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public interface ReportExporterProviderHook extends Hook {

	public List<ReportExporter> getExporters(ReportDto report);
}
