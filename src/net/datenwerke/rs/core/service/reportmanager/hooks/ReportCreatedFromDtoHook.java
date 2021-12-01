package net.datenwerke.rs.core.service.reportmanager.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

public interface ReportCreatedFromDtoHook extends Hook {

	void reportCreated(ReportDto reportDto, Report report);

	void reportMerged(ReportDto reportDto, Report report);

	void reportCreatedUnmanaged(ReportDto reportDto, Report report);

}
