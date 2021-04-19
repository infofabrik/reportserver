package net.datenwerke.rs.core.client.reportmanager.hooks;

import java.util.Optional;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.helper.reportselector.ReportSelectionDialog;
import net.datenwerke.rs.core.client.reportmanager.helper.reportselector.ReportSelectionDialog.RepositoryProviderConfig;

public interface ReportSelectionRepositoryProviderHook extends Hook {

   void addCards(ReportSelectionDialog dialog, Optional<ReportDto> selectedReport, RepositoryProviderConfig[] configs);
}
