package net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.filters;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportServerJobFilterDto;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.ScheduledReportListPanel;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.SchedulerAdminModule;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hooks.ScheduledReportListFilter;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterCriteriaDto;

public class ReportIdScheduledEntriesFilter implements ScheduledReportListFilter {

	private FormFieldProviderHook reportIdHook;
	private Widget reportIdField;
	
	public Iterable<Widget> getFilter(final ScheduledReportListPanel scheduledReportListPanel){
		reportIdHook = SimpleForm.getResponsibleHooker(String.class);
		reportIdField = reportIdHook.createFormField();
		
		reportIdHook.addValueChangeHandler(new ValueChangeHandler() {
			@Override
			public void onValueChange(ValueChangeEvent event) {
				scheduledReportListPanel.reload();
			}
		});
		
		FieldLabel jobIdLabel = new FieldLabel(reportIdField, SchedulerMessages.INSTANCE.gridIdLabel());
		jobIdLabel.setLabelWidth(120);
		
		ArrayList<Widget> widgets = new ArrayList<Widget>();
		widgets.add(jobIdLabel);
		
		return widgets;
	}
	
	public void configure(ScheduledReportListPanel scheduledReportListPanel, JobFilterConfigurationDto config, List<JobFilterCriteriaDto> addCriterions){
		if(config instanceof ReportServerJobFilterDto){
			((ReportServerJobFilterDto)config).setReportId(null);
		
			if(null != reportIdHook){
				String id = (String) reportIdHook.getValue(reportIdField);
				
				if( null != id && ! "".equals(id.trim()))
					((ReportServerJobFilterDto)config).setReportId(id);
			}
		}
	}

	@Override
	public boolean appliesTo(String panelName) {
		return SchedulerAdminModule.ADMIN_FILTER_PANEL.equals(panelName);
	}

	
	
	
}
