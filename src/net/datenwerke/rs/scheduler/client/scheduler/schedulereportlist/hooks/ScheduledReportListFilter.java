package net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hooks;

import java.util.List;

import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.ScheduledReportListPanel;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterCriteriaDto;

public interface ScheduledReportListFilter extends Hook  {

	Iterable<Widget> getFilter(ScheduledReportListPanel scheduledReportListPanel);

	void configure(ScheduledReportListPanel scheduledReportListPanel,
			JobFilterConfigurationDto jobFilterConfig, List<JobFilterCriteriaDto> addCriterions);

	boolean appliesTo(String panelName);
}
