package net.datenwerke.rs.dashboard.client.dashboard.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.treedb.dnd.UITreeDragDropConfiguration;
import net.datenwerke.rs.dashboard.client.dashboard.DashboardTreeManagerDao;
import net.datenwerke.rs.dashboard.client.dashboard.DashboardUiModule;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardFolderDto;
import net.datenwerke.rs.dashboard.client.dashboard.ui.admin.DashboardManagerMainPanel;

public class FullTreeProvider implements Provider<ManagerHelperTree>{

	private final BasicTreeProvider basicTreeProvider;
	private final DashboardTreeManagerDao treeHandler;
	private final DashboardManagerMainPanel mainPanel;
	
	@Inject
	public FullTreeProvider(
			BasicTreeProvider basicTreeProvider,
			DashboardTreeManagerDao treeHandler,
			DashboardManagerMainPanel mainPanel
		){
		
		this.basicTreeProvider = basicTreeProvider;
		this.treeHandler = treeHandler;
		this.mainPanel = mainPanel;
	}

	public ManagerHelperTree get() {
		/* build tree */
		final ManagerHelperTree tree = basicTreeProvider.get();
		tree.getStore().enableDtoAwareness(true);
				
		/* dnd */
		UITreeDragDropConfiguration dndConfig = new UITreeDragDropConfiguration();
		dndConfig.addDropTarget(DashboardFolderDto.class);
		tree.enableDragDrop(treeHandler, dndConfig);
		tree.enableClipboardProvider();

		/* set selections */
		tree.setSelectionProvider(mainPanel);
		
		/* double click */
		tree.enableDoubleClickAction();
		
		/* history location */
		tree.setHistoryLocation(DashboardUiModule.DASHBOARD_HISTORY_TOKEN);
		
		/* tree menu */
		tree.configureMenuProvider(DashboardUiModule.ADMIN_TREE_MENU_NAME);
		
		return tree;
	}
}
