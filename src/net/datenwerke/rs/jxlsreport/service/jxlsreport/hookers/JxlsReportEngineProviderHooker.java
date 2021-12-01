package net.datenwerke.rs.jxlsreport.service.jxlsreport.hookers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.datenwerke.rs.core.service.reportmanager.engine.ReportEngine;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportEngineProviderHook;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.JxlsReportEngine;

public class JxlsReportEngineProviderHooker implements ReportEngineProviderHook{

	@Override
	public Collection<? extends Class<? extends ReportEngine>> getReportEngines() {
		Set<Class<? extends ReportEngine>> engines = new HashSet<Class<? extends ReportEngine>>();		

		engines.add(JxlsReportEngine.class);

		return engines;
	}

}
