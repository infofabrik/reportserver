package net.datenwerke.rs.core.service.reportmanager.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

public interface VariantCreatorHook extends Hook {

	void newVariantCreated(Report referenceReport, Report adjustedReport, Report variant);
	
	void temporaryVariantCreated(Report referenceReport, Report adjustedReport, Report variant);

}
