package net.datenwerke.rs.core.client.datasinkmanager.ui;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeNavigationPanel;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.datasinkmanager.provider.annotations.DatasinkManagerAdminViewTree;

import com.google.inject.Inject;

/**
 * 
 *
 */
public class DatasinkManagerTreePanel extends AbstractTreeNavigationPanel {

	@Inject
	public DatasinkManagerTreePanel(
		HookHandlerService hookHandler,
		@DatasinkManagerAdminViewTree UITree tree
		){
		super(hookHandler, tree);
	}
	
}
