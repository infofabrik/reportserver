package net.datenwerke.rs.scheduler.client.scheduler;

import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;

import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto.ReportScheduleJobListInformation;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto;


public interface SchedulerUiService {

	ColumnModel<ReportScheduleJobListInformation> createReportScheduleListColumnModel();

	void transformLoadConfig(PagingLoadConfig plc,
			JobFilterConfigurationDto jobFilterConfig);

	ColumnModel<ReportScheduleJobListInformation> createReportScheduleListColumnModel(boolean displayJobId,
			boolean displayOwnerColumn, boolean displayScheduledByColumn);

}
