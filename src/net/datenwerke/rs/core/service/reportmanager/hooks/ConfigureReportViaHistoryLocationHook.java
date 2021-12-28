package net.datenwerke.rs.core.service.reportmanager.hooks;

import net.datenwerke.gf.client.history.HistoryLocation;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

public interface ConfigureReportViaHistoryLocationHook extends Hook {

   void adjustReport(Report report, HistoryLocation location);

}
