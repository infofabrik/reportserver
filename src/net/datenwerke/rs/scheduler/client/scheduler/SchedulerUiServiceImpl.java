package net.datenwerke.rs.scheduler.client.scheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import net.datenwerke.rs.core.client.i18tools.FormatUiHelper;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto.ReportScheduleJobListInformation;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto.ReportScheduleJobListInformationPA;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.OrderDto;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.util.Format;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;

public class SchedulerUiServiceImpl implements SchedulerUiService {

	private static ReportScheduleJobListInformationPA scheduleInfoPa = GWT.create(ReportScheduleJobListInformationPA.class);
	private FormatUiHelper formatUiHelper;
	
	@Inject
	public SchedulerUiServiceImpl(FormatUiHelper formatUiHelper) {
		this.formatUiHelper = formatUiHelper;
	}
	
	@Override
	public ColumnModel<ReportScheduleJobListInformation> createReportScheduleListColumnModel() {
		return createReportScheduleListColumnModel(false, false, false);
	}
	
	@Override
	public ColumnModel<ReportScheduleJobListInformation> createReportScheduleListColumnModel(boolean displayJobId, boolean displayExecutorColumn, boolean displayScheduledByColumn) {
		List<ColumnConfig<ReportScheduleJobListInformation,?>> colConfig = new ArrayList<ColumnConfig<ReportScheduleJobListInformation,?>>();
		
		if(displayJobId){
			ColumnConfig<ReportScheduleJobListInformation,Long> idColumn = new ColumnConfig<ReportScheduleJobListInformation,Long>(scheduleInfoPa.jobId(), 80, SchedulerMessages.INSTANCE.jobId());
			idColumn.setMenuDisabled(true);
			colConfig.add(idColumn);
		}
		
		ColumnConfig<ReportScheduleJobListInformation,Long> idColumn = new ColumnConfig<ReportScheduleJobListInformation,Long>(scheduleInfoPa.reportId(), 80, SchedulerMessages.INSTANCE.gridIdLabel());
		idColumn.setMenuDisabled(true);
		colConfig.add(idColumn);
		
		ColumnConfig<ReportScheduleJobListInformation,ReportScheduleJobListInformation> descriptionConfig = new ColumnConfig<ReportScheduleJobListInformation,ReportScheduleJobListInformation>(new IdentityValueProvider<ReportScheduleJobListInformation>(scheduleInfoPa.jobTitle().getPath()), 270, SchedulerMessages.INSTANCE.jobTitle());
		descriptionConfig.setCell(new AbstractCell<ReportScheduleJobListInformation>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					ReportScheduleJobListInformation value, SafeHtmlBuilder sb) {
				String header = value.getJobTitle();
				String description = null == value.getJobDescription() ? SchedulerMessages.INSTANCE.noDescription() : Format.ellipse(value.getJobDescription(), 270);
				sb.appendHtmlConstant("<div class=\"rs-list-name-desc\"><div class=\"title\">" + header + "</div><div class=\"description\">" + description + "</div></div>");
			}
		});
		descriptionConfig.setMenuDisabled(true);
		colConfig.add(descriptionConfig);
		
		if (displayExecutorColumn){
			ColumnConfig<ReportScheduleJobListInformation,ReportScheduleJobListInformation> executorConfig = new ColumnConfig<ReportScheduleJobListInformation,ReportScheduleJobListInformation>(new IdentityValueProvider<ReportScheduleJobListInformation>(), 150, SchedulerMessages.INSTANCE.executor());
			executorConfig.setCell(new AbstractCell<ReportScheduleJobListInformation>() {
				@Override
				public void render(com.google.gwt.cell.client.Cell.Context context,
						ReportScheduleJobListInformation value, SafeHtmlBuilder sb) {
					if (!value.isExecutorDeleted()) {
						String name = value.getExecutorName();
						if(null != name)
							sb.appendEscaped(name);
					} else
						sb.appendEscaped(SchedulerMessages.INSTANCE.deletedExecutor());
					
				}
			});
			executorConfig.setMenuDisabled(true);
			colConfig.add(executorConfig);
		}
		
		if (displayScheduledByColumn) {
			ColumnConfig<ReportScheduleJobListInformation,ReportScheduleJobListInformation> scheduledByConfig = new ColumnConfig<ReportScheduleJobListInformation,ReportScheduleJobListInformation>(new IdentityValueProvider<ReportScheduleJobListInformation>(), 150, SchedulerMessages.INSTANCE.scheduledBy());
			scheduledByConfig.setCell(new AbstractCell<ReportScheduleJobListInformation>() {
				@Override
				public void render(com.google.gwt.cell.client.Cell.Context context,
						ReportScheduleJobListInformation value, SafeHtmlBuilder sb) {
					if (!value.isScheduledByDeleted()) {
						String name = value.getScheduledByName();
						if(null != name)
							sb.appendEscaped(name);
					} else
						sb.appendEscaped(SchedulerMessages.INSTANCE.deletedScheduledBy());
				}
			});
			scheduledByConfig.setMenuDisabled(true);
			colConfig.add(scheduledByConfig);
		}
		
		ColumnConfig<ReportScheduleJobListInformation,Date> lastColumn = new ColumnConfig<ReportScheduleJobListInformation,Date>(scheduleInfoPa.lastScheduled(), 128, SchedulerMessages.INSTANCE.gridLastScheduled());
		lastColumn.setCell(new DateCell(formatUiHelper.getShortDateTimeFormat()));
		lastColumn.setMenuDisabled(true);
		colConfig.add(lastColumn);
		
		ColumnConfig<ReportScheduleJobListInformation,Date> nextColumn = new ColumnConfig<ReportScheduleJobListInformation,Date>(scheduleInfoPa.nextScheduled(), 128, SchedulerMessages.INSTANCE.gridNextSchedule());
		nextColumn.setCell(new DateCell(formatUiHelper.getShortDateTimeFormat()));
		nextColumn.setMenuDisabled(true);
		colConfig.add(nextColumn);
		
		return new ColumnModel<ReportScheduleJobListInformation>(colConfig);
	}
	

	@Override
	public void transformLoadConfig(PagingLoadConfig plc, JobFilterConfigurationDto jobFilterConfig) {
		jobFilterConfig.setOffset(plc.getOffset());
		jobFilterConfig.setLimit(plc.getLimit());
		
		if(! plc.getSortInfo().isEmpty()){
			SortInfo info = plc.getSortInfo().get(0);
			
			if(SortDir.ASC == info.getSortDir())
				jobFilterConfig.setOrder(OrderDto.ASC);
			else if(SortDir.DESC == info.getSortDir())
				jobFilterConfig.setOrder(OrderDto.DESC);
			
			if(null != info.getSortField())
				jobFilterConfig.setSortField(info.getSortField());
		}
	}

}
