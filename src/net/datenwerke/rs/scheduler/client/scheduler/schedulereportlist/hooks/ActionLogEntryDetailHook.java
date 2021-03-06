package net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hooks;

import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.ScheduledReportListPanel;
import net.datenwerke.scheduler.client.scheduler.dto.history.ActionEntryDto;

public interface ActionLogEntryDetailHook extends Hook {

   boolean consumes(ActionEntryDto action);

   Widget getCard(ActionEntryDto action, ScheduledReportListPanel scheduledReportListPanel);

}
