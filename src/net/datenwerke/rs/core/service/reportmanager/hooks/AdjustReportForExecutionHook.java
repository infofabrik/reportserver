package net.datenwerke.rs.core.service.reportmanager.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

public interface AdjustReportForExecutionHook extends Hook {

   public void adjust(Report realReport);

   public void adjust(ReportDto tmpReport);

}
