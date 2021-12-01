package net.datenwerke.rs.core.service.reportmanager.engine.hooks;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

public interface ReportExecutionConfigFromPropertyMapHook extends Hook {

	public Collection<ReportExecutionConfig> parse(Report report, HttpServletRequest request, Map<String,String[]> properties);
}
