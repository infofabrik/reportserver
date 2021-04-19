package net.datenwerke.rs.scheduleasfile.client.scheduleasfile.filter;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.filter.dto.TeamSpaceReportJobFilterDto;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.locale.ScheduleAsFileMessages;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.ScheduledReportListPanel;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hooks.ScheduledReportListFilter;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterCriteriaDto;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

public class TeamspaceFilter implements ScheduledReportListFilter {
	
	private FormFieldProviderHook hook;
	private Widget field;
	
	@Override
	public Iterable<Widget> getFilter(final ScheduledReportListPanel scheduledReportListPanel) {
		hook = SimpleForm.getResponsibleHooker(TeamSpaceDto.class);
		field = hook.createFormField();
		
		hook.addValueChangeHandler(new ValueChangeHandler() {
			@Override
			public void onValueChange(ValueChangeEvent event) {
				scheduledReportListPanel.reload();
			}
		});
		
		ArrayList<Widget> widgets = new ArrayList<Widget>();
		FieldLabel status = new FieldLabel(field, ScheduleAsFileMessages.INSTANCE.teamspace() );
		status.setLabelWidth(120);
		widgets.add(status);
		
		return widgets;
	}

	@Override
	public void configure(ScheduledReportListPanel scheduledReportListPanel,
			JobFilterConfigurationDto jobFilterConfig, List<JobFilterCriteriaDto> addCriterions) {
		if(null != hook && null != hook.getValue(field)){
			TeamSpaceDto ts = (TeamSpaceDto) hook.getValue(field);
			
			TeamSpaceReportJobFilterDto addCrit = new TeamSpaceReportJobFilterDto();
			addCrit.setTeamspaceId(ts.getId());
			addCriterions.add(addCrit);
		}
	}

	@Override
	public boolean appliesTo(String panelName) {
		return true;
	}

}
