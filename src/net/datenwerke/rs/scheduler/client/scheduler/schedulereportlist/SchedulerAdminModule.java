package net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.administration.interfaces.AdminModule;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

@Singleton
public class SchedulerAdminModule implements AdminModule{

	public static final String ADMIN_FILTER_PANEL = "ADMIN_FILTER_PANEL";
	
	private final ScheduledReportListPanelFactory schedulerAdminPanelFactory;

	private ScheduledReportListPanel schedulerAdminPanel;

	@Inject
	public SchedulerAdminModule(
		ScheduledReportListPanelFactory schedulerAdminPanelFactory
		) {
		
		/* store objects */
		this.schedulerAdminPanelFactory = schedulerAdminPanelFactory;
	}
	
	@Override
	public Widget getMainWidget() {
		if(null == this.schedulerAdminPanel){
			schedulerAdminPanel = schedulerAdminPanelFactory.create(ADMIN_FILTER_PANEL, true, true);
			schedulerAdminPanel.load();
		}
		return schedulerAdminPanel;
	}

	@Override
	public ImageResource getNavigationIcon() {
		return BaseIcon.CLOCK_O.toImageResource();
	}

	@Override
	public String getNavigationText() {
		return SchedulerMessages.INSTANCE.scheduler();
	}

	@Override
	public void notifyOfSelection() {
		if(null != schedulerAdminPanel)
			schedulerAdminPanel.reload();
	}
}
