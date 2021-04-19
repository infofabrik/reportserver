package net.datenwerke.rs.core.client.reportexporter.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public interface ReportExporterPreExportHook extends Hook{

	String isExportable(ReportDto report);

}
