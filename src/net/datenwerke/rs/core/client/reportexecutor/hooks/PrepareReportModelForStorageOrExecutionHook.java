package net.datenwerke.rs.core.client.reportexecutor.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public interface PrepareReportModelForStorageOrExecutionHook extends Hook {

	public boolean consumes(ReportDto report);
	
	public void prepareForExecutionOrStorage(ReportDto report, String executeToken);
}
