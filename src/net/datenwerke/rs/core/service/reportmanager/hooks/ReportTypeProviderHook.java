package net.datenwerke.rs.core.service.reportmanager.hooks;

import java.util.Collection;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

public interface ReportTypeProviderHook extends Hook {

   Collection<? extends Class<? extends Report>> getReportTypes();

}
