package net.datenwerke.rs.dashboard.client.dashboard.ui.admin;

import com.google.inject.Inject;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeNavigationPanel;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.dashboard.client.dashboard.provider.annotations.DashboardManagerAdminViewTree;

/**
 * 
 *
 */
public class DashboardManagerTreePanel extends AbstractTreeNavigationPanel {

	@Inject
	public DashboardManagerTreePanel(
		HookHandlerService hookHandler,
		@DashboardManagerAdminViewTree UITree tree
		){
		super(hookHandler, tree);
	}
	
}
