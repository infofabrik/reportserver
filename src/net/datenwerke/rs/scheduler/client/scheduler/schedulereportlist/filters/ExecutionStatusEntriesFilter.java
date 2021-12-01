package net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.filters;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCEnumList;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportServerJobFilterDto;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.ScheduledReportListPanel;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hooks.ScheduledReportListFilter;
import net.datenwerke.scheduler.client.scheduler.dto.JobExecutionStatusDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterCriteriaDto;

public class ExecutionStatusEntriesFilter implements ScheduledReportListFilter {

	private FormFieldProviderHook statusHook;
	private Widget statusField;
	
	@Inject
	public ExecutionStatusEntriesFilter(
		) {
	}
	
	public Iterable<Widget> getFilter(final ScheduledReportListPanel scheduledReportListPanel){
		statusHook = SimpleForm.getResponsibleHooker(List.class, new SFFCEnumList(JobExecutionStatusDto.class){
			@Override
			public boolean allowBlank() {
				return true;
			}
		});
		statusField = statusHook.createFormField();
		
		statusHook.addValueChangeHandler(event -> scheduledReportListPanel.reload());
		
		FieldLabel status = new FieldLabel(statusField, SchedulerMessages.INSTANCE.status());
		status.setLabelWidth(120);
		
		ArrayList<Widget> widgets = new ArrayList<Widget>();
		widgets.add(status);
		
		return widgets;
	}
	
	public void configure(ScheduledReportListPanel scheduledReportListPanel, JobFilterConfigurationDto config, List<JobFilterCriteriaDto> addCriterions){
		if(config instanceof ReportServerJobFilterDto){
			config.setExecutionStatus(null);
			
			if(null != statusHook){
				JobExecutionStatusDto status = (JobExecutionStatusDto) statusHook.getValue(statusField);
				config.setExecutionStatus(status);
			}
		}
	}

	@Override
	public boolean appliesTo(String panelName) {
		return true;
	}

	
	
	
}
