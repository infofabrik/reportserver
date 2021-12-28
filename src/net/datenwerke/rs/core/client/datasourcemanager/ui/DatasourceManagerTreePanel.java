package net.datenwerke.rs.core.client.datasourcemanager.ui;

import com.google.inject.Inject;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeNavigationPanel;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.datasourcemanager.provider.annotations.DatasourceManagerAdminViewTree;

/**
 * 
 *
 */
public class DatasourceManagerTreePanel extends AbstractTreeNavigationPanel {

	@Inject
	public DatasourceManagerTreePanel(
		HookHandlerService hookHandler,
		@DatasourceManagerAdminViewTree UITree tree
		){
		super(hookHandler, tree);
	}
	
}
