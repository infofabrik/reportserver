package net.datenwerke.rs.core.client.datasinkmanager.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.treedb.dnd.UITreeDragDropConfiguration;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkTreeManagerDao;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkUIModule;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkFolderDto;
import net.datenwerke.rs.core.client.datasinkmanager.ui.DatasinkManagerMainPanel;

public class FullTreeProvider implements Provider<ManagerHelperTree>{

	private final BasicTreeProvider basicTreeProvider;
	private final DatasinkTreeManagerDao datasinkTreeHandler;
	private final DatasinkManagerMainPanel mainPanel;
	
	@Inject
	public FullTreeProvider(
			BasicTreeProvider basicTreeProvider,
			DatasinkTreeManagerDao datasinkTreeHandler,
			DatasinkManagerMainPanel mainPanel
		){
		
		this.basicTreeProvider = basicTreeProvider;
		this.datasinkTreeHandler = datasinkTreeHandler;
		this.mainPanel = mainPanel;
	}

	public ManagerHelperTree get() {
		/* build tree */
		final ManagerHelperTree tree = basicTreeProvider.get();
		tree.getStore().enableDtoAwareness(true);
				
		/* dnd */
		UITreeDragDropConfiguration dndConfig = new UITreeDragDropConfiguration();
		dndConfig.addDropTarget(DatasinkFolderDto.class);
		tree.enableDragDrop(datasinkTreeHandler, dndConfig);
		tree.enableClipboardProvider();

		/* set selections */
		tree.setSelectionProvider(mainPanel);
		
		/* double click */
		tree.enableDoubleClickAction();
		
		/* history location */
		tree.setHistoryLocation(DatasinkUIModule.DATASINK_FAV_HISTORY_TOKEN);
		
		/* tree menu */
		tree.configureMenuProvider(DatasinkUIModule.ADMIN_TREE_MENU_NAME);
		
		return tree;
	}
}
