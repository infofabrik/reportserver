package net.datenwerke.rs.birt.service.reportengine.hookers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.datenwerke.rs.birt.service.reportengine.BirtReportEngine;
import net.datenwerke.rs.core.service.reportmanager.engine.ReportEngine;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportEngineProviderHook;

public class BirtReportEngineProviderHooker implements ReportEngineProviderHook{

	@Override
	public Collection<? extends Class<? extends ReportEngine>> getReportEngines() {
		Set<Class<? extends ReportEngine>> engines = new HashSet<Class<? extends ReportEngine>>();		

		engines.add(BirtReportEngine.class);

		return engines;
	}

}
