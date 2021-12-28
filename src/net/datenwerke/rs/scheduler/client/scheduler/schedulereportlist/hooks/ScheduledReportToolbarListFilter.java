package net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hooks;

import java.util.List;

import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwHookableToolbar;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.ScheduledReportListPanel;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterCriteriaDto;

public interface ScheduledReportToolbarListFilter extends Hook {

   boolean addToToolbar(ScheduledReportListPanel scheduledReportListPanel, DwHookableToolbar mainToolbar);

   void configure(ScheduledReportListPanel scheduledReportListPanel, JobFilterConfigurationDto jobFilterConfig,
         List<JobFilterCriteriaDto> addCriterions);

   boolean appliesTo(String panelName);
}
