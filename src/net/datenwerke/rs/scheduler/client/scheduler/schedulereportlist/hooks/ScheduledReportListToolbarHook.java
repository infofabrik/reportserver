package net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hooks;

import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.ScheduledReportListPanel;

public interface ScheduledReportListToolbarHook extends Hook {

   public void statusBarToolbarHook_addLeft(ToolBar toolbar, ScheduledReportListPanel reportListPanel);

   public void statusBarToolbarHook_addRight(ToolBar toolbar, ScheduledReportListPanel reportListPanel);
}
