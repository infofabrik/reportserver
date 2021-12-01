package net.datenwerke.rs.dashboard.client.dashboard.ui.admin;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeMainPanel;
import net.datenwerke.rs.dashboard.client.dashboard.DashboardTreeManagerDao;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * The actual implementation of the dashboard managers main component.
 * 
 *
 */
@Singleton
public class DashboardManagerMainPanel extends AbstractTreeMainPanel {

	@Inject
	public DashboardManagerMainPanel(
		DashboardTreeManagerDao manager
		){
	
		super(manager);
	}
	
	@Override
	protected String getToolbarName() {
		return "dashboard:admin:view:toolbar";
	}
}
