package net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.filters;

import java.util.List;

import com.sencha.gxt.widget.core.client.button.ToggleButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwToggleButton;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwHookableToolbar;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportServerJobFilterDto;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.ScheduledReportListPanel;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.SchedulerClientModule;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.hooks.ScheduledReportToolbarListFilter;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterCriteriaDto;

public class MyOrToMeScheduledEntriesFilter implements ScheduledReportToolbarListFilter {

	private DwToggleButton myBtn;
	private DwToggleButton toMeBtn;
	
	public boolean addToToolbar(final ScheduledReportListPanel scheduledReportListPanel, DwHookableToolbar mainToolbar){
		myBtn = new DwToggleButton(SchedulerMessages.INSTANCE.myBtnLabel());
		myBtn.setIcon(BaseIcon.ARROWS_OUT);
		myBtn.setToolTip(SchedulerMessages.INSTANCE.byMeSchedulerToolTip());
		myBtn.setValue(true);
		
		toMeBtn = new DwToggleButton(SchedulerMessages.INSTANCE.toMeBtnLabel());
		toMeBtn.setIcon(BaseIcon.ARROWS_IN);
		toMeBtn.setToolTip(SchedulerMessages.INSTANCE.toMeSchedulerToolTip());
		
		mainToolbar.add(myBtn);
		mainToolbar.add(toMeBtn);
		
		final ToggleButton[] filterButtons = new DwToggleButton[]{myBtn, toMeBtn};
		for(final ToggleButton filterBtn : filterButtons){
			filterBtn.addSelectHandler(new SelectHandler() {
				@Override
				public void onSelect(SelectEvent event) {
					if(myBtn == filterBtn)
						toMeBtn.setValue(false, false);
					else
						myBtn.setValue(false, false);
					
					scheduledReportListPanel.reload();
				}
			});
		}
		
		return true;
	}
	
	public void configure(ScheduledReportListPanel scheduledReportListPanel, JobFilterConfigurationDto config, List<JobFilterCriteriaDto> addCriterions){
		if(config instanceof ReportServerJobFilterDto){
			((ReportServerJobFilterDto)config).setFromCurrentUser(false);
			((ReportServerJobFilterDto)config).setToCurrentUser(false);

			if(null != myBtn && myBtn.getValue())
				((ReportServerJobFilterDto)config).setFromCurrentUser(true);
			else if(null == myBtn) // init
				((ReportServerJobFilterDto)config).setFromCurrentUser(true);
				
			((ReportServerJobFilterDto)config).setToCurrentUser(
					! ((ReportServerJobFilterDto)config).isFromCurrentUser()
			);
		}
	}

	@Override
	public boolean appliesTo(String panelName) {
		return SchedulerClientModule.CLIENT_FILTER_PANEL.equals(panelName);
	}
	
	
}
