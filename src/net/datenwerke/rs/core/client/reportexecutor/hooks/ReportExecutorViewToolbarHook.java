package net.datenwerke.rs.core.client.reportexecutor.hooks;


import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorInformation;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanel;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public interface ReportExecutorViewToolbarHook extends Hook {

	public boolean reportPreviewViewToolbarHook_addLeft(ToolBar toolbar, ReportDto report, ReportExecutorInformation info, ReportExecutorMainPanel mainPanel);
	
	public boolean reportPreviewViewToolbarHook_addRight(ToolBar toolbar, ReportDto report, ReportExecutorInformation info, ReportExecutorMainPanel mainPanel);

	void reportPreviewViewToolbarHook_reportUpdated(ReportDto report,
			ReportExecutorInformation info);
	
}
