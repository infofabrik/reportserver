package net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.filters;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.ScheduledReportListPanel;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.SchedulerAdminModule;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hooks.ScheduledReportListFilter;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterCriteriaDto;

public class JobNameScheduledEntriesFilter implements ScheduledReportListFilter {

	private FormFieldProviderHook jobNameHook;
	private Widget jobNameField;
	
	public Iterable<Widget> getFilter(final ScheduledReportListPanel scheduledReportListPanel){
		jobNameHook = SimpleForm.getResponsibleHooker(String.class);
		jobNameField = jobNameHook.createFormField();
		
		jobNameHook.addValueChangeHandler(event -> scheduledReportListPanel.reload());
		
		FieldLabel jobNameLabel = new FieldLabel(jobNameField, SchedulerMessages.INSTANCE.jobTitle());
		jobNameLabel.setLabelWidth(120);
		
		ArrayList<Widget> widgets = new ArrayList<Widget>();
		widgets.add(jobNameLabel);
		
		return widgets;
	}
	
	public void configure(ScheduledReportListPanel scheduledReportListPanel, JobFilterConfigurationDto config, List<JobFilterCriteriaDto> addCriterions){
		config.setJobId(null);
		
		if(null != jobNameHook){
			String jobName = (String) jobNameHook.getValue(jobNameField);
			
			if( null != jobName && ! "".equals(jobName.trim()))
				config.setJobTitle(jobName);
		}
	}

	@Override
	public boolean appliesTo(String panelName) {
		return SchedulerAdminModule.ADMIN_FILTER_PANEL.equals(panelName);
	}

	
	
	
}
