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

public class JobIdScheduledEntriesFilter implements ScheduledReportListFilter {

	private FormFieldProviderHook jobIdHook;
	private Widget jobIdField;
	
	public Iterable<Widget> getFilter(final ScheduledReportListPanel scheduledReportListPanel){
		jobIdHook = SimpleForm.getResponsibleHooker(String.class);
		jobIdField = jobIdHook.createFormField();
		
		jobIdHook.addValueChangeHandler(event -> scheduledReportListPanel.reload());
		
		FieldLabel jobIdLabel = new FieldLabel(jobIdField, SchedulerMessages.INSTANCE.jobId());
		jobIdLabel.setLabelWidth(120);
		
		ArrayList<Widget> widgets = new ArrayList<Widget>();
		widgets.add(jobIdLabel);
		
		return widgets;
	}
	
	public void configure(ScheduledReportListPanel scheduledReportListPanel, JobFilterConfigurationDto config, List<JobFilterCriteriaDto> addCriterions){
		config.setJobId(null);
		
		if(null != jobIdHook){
			String id = (String) jobIdHook.getValue(jobIdField);
			
			if( null != id && ! "".equals(id.trim()))
				config.setJobId(id);
		}
	}

	@Override
	public boolean appliesTo(String panelName) {
		return SchedulerAdminModule.ADMIN_FILTER_PANEL.equals(panelName);
	}

	
	
	
}
