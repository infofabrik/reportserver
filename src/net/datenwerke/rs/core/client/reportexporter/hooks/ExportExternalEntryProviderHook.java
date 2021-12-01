package net.datenwerke.rs.core.client.reportexporter.hooks;

import com.sencha.gxt.widget.core.client.menu.Menu;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorInformation;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanel;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public interface ExportExternalEntryProviderHook extends Hook {

	void getMenuEntry(Menu menu, ReportDto report,
			ReportExecutorInformation info, ReportExecutorMainPanel mainPanel);

}
