package net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.filters;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCEnumList;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportServerJobFilterDto;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.ScheduledReportListPanel;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hooks.ScheduledReportListFilter;
import net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterCriteriaDto;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

public class FailedLastTimeEntriesFilter implements ScheduledReportListFilter {

	private FormFieldProviderHook statusHook;
	private Widget outcomeField;
	
	@Inject
	public FailedLastTimeEntriesFilter(
		) {
	}
	
	public Iterable<Widget> getFilter(final ScheduledReportListPanel scheduledReportListPanel){
		statusHook = SimpleForm.getResponsibleHooker(List.class, new SFFCEnumList(OutcomeDto.class){
			@Override
			public boolean allowBlank() {
				return true;
			}
		});
		outcomeField = statusHook.createFormField();
		
		statusHook.addValueChangeHandler(new ValueChangeHandler() {
			@Override
			public void onValueChange(ValueChangeEvent event) {
				scheduledReportListPanel.reload();
			}
		});
		
		FieldLabel status = new FieldLabel(outcomeField, SchedulerMessages.INSTANCE.lastOutcome());
		status.setLabelWidth(120);
		
		ArrayList<Widget> widgets = new ArrayList<Widget>();
		widgets.add(status);
		
		return widgets;
	}
	
	public void configure(ScheduledReportListPanel scheduledReportListPanel, JobFilterConfigurationDto config, List<JobFilterCriteriaDto> addCriterions){
		if(config instanceof ReportServerJobFilterDto){
			config.setLastOutcome(null);
			
			if(null != statusHook){
				OutcomeDto outcome = (OutcomeDto) statusHook.getValue(outcomeField);
				config.setLastOutcome(outcome);
			}
		}
	}

	@Override
	public boolean appliesTo(String panelName) {
		return true;
	}

	
	
	
}
