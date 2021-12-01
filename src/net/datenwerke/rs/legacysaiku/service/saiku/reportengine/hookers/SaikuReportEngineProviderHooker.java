package net.datenwerke.rs.legacysaiku.service.saiku.reportengine.hookers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.datenwerke.rs.core.service.reportmanager.engine.ReportEngine;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportEngineProviderHook;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.SaikuReportEngine;

public class SaikuReportEngineProviderHooker implements
		ReportEngineProviderHook {

	@Override
	public Collection<? extends Class<? extends ReportEngine>> getReportEngines() {
		Set<Class<? extends ReportEngine>> engines = new HashSet<Class<? extends ReportEngine>>();		

		engines.add(SaikuReportEngine.class);

		return engines;
	}

}
