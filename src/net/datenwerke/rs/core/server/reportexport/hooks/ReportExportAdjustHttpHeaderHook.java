package net.datenwerke.rs.core.server.reportexport.hooks;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

@HookConfig
public interface ReportExportAdjustHttpHeaderHook extends Hook {

   void adjustHeaders(Report report, CompiledReport executedReport, HttpServletRequest req, HttpServletResponse resp);

}
