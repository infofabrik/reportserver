package net.datenwerke.rs.core.service.reportmanager.hooks;

import java.util.Collection;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.service.reportmanager.engine.ReportEngine;

public interface ReportEngineProviderHook extends Hook {

	Collection<? extends Class<? extends ReportEngine>> getReportEngines();

}
