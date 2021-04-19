package net.datenwerke.rs.core.client.reportmanager.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.helper.reportselector.ReportSelectionDialog;
import net.datenwerke.rs.core.client.reportmanager.helper.reportselector.ReportSelectionDialog.RepositoryProviderConfig;

public interface ReportSelectionRepositoryProviderHook extends Hook {

	void addCards(ReportSelectionDialog dialog, ReportDto selectedReport, RepositoryProviderConfig[] configs);

}
