package net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hooks;

import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.ScheduledReportListPanel;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto.ReportScheduleJobInformation;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto.ReportScheduleJobListInformation;

public interface ScheduledReportListDetailToolbarHook extends Hook {


	public void statusBarToolbarHook_addLeft(ToolBar toolbar, ReportScheduleJobListInformation info, ReportScheduleJobInformation detailInfo, ScheduledReportListPanel reportListPanel);
	
	public void statusBarToolbarHook_addRight(ToolBar toolbar, ReportScheduleJobListInformation info,ReportScheduleJobInformation detailInfo, ScheduledReportListPanel reportListPanel);
}
