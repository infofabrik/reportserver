package net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist;

import com.google.inject.assistedinject.Assisted;

public interface ScheduledReportListPanelFactory {

	public ScheduledReportListPanel create(
			String name,
			@Assisted("displayExecutorColumn") boolean displayExecutorColumn, 
			@Assisted("displayScheduledByColumn") boolean displayScheduledByColumn);
	
}
