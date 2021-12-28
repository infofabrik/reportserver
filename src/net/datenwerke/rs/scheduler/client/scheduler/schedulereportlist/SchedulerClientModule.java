package net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.homepage.modules.ClientMainModuleImpl;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

@Singleton
public class SchedulerClientModule extends ClientMainModuleImpl {
	
	public static final String CLIENT_FILTER_PANEL = "CLIENT_FILTER_PANEL";
	
	private final ScheduledReportListPanelFactory schedulerMyTasksPanelFactory;
	
	private ScheduledReportListPanel schedulerPanel;
	
	@Inject
	public SchedulerClientModule(
		ScheduledReportListPanelFactory scheduledReportListPanelFactory
	) {
		
		/* store objects */
		this.schedulerMyTasksPanelFactory = scheduledReportListPanelFactory;
	}
	
	@Override
	public ImageResource getIcon() {
		return BaseIcon.CLOCK_O.toImageResource();
	}
	
	public Widget getMainWidget() {
		if(null == this.schedulerPanel){
			schedulerPanel = schedulerMyTasksPanelFactory.create(CLIENT_FILTER_PANEL, false, false);
			schedulerPanel.load();
		}
		return schedulerPanel;
	}

	public String getModuleName() {
		return SchedulerMessages.INSTANCE.scheduler();
	}

	public void selected() {
		if(null != schedulerPanel)
			schedulerPanel.reload();
	}

	@Override
	public boolean isRecyclable() {
		return true;
	}

}
