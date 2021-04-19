package net.datenwerke.rs.legacysaiku.service.hooker;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportTypeProviderHook;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;

public class SaikuReportTypeProviderHooker implements ReportTypeProviderHook {

	@Override
	public Collection<? extends Class<? extends Report>> getReportTypes() {
		Set<Class<? extends Report>> types = new HashSet<Class<? extends Report>>();

		types.add(SaikuReport.class);

		return types;
	}

}
