package net.datenwerke.rs.core.client.datasourcemanager.provider;

import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.treedb.dnd.UITreeDragDropConfiguration;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceTreeManagerDao;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceUIModule;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceFolderDto;
import net.datenwerke.rs.core.client.datasourcemanager.ui.DatasourceManagerMainPanel;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class FullTreeProvider implements Provider<ManagerHelperTree>{

	private final BasicTreeProvider basicTreeProvider;
	private final DatasourceTreeManagerDao datasourceTreeHandler;
	private final DatasourceManagerMainPanel mainPanel;
	
	@Inject
	public FullTreeProvider(
			BasicTreeProvider basicTreeProvider,
			DatasourceTreeManagerDao datasourceTreeHandler,
			DatasourceManagerMainPanel mainPanel
		){
		
		this.basicTreeProvider = basicTreeProvider;
		this.datasourceTreeHandler = datasourceTreeHandler;
		this.mainPanel = mainPanel;
	}

	public ManagerHelperTree get() {
		/* build tree */
		final ManagerHelperTree tree = basicTreeProvider.get();
		tree.getStore().enableDtoAwareness(true);
				
		/* dnd */
		UITreeDragDropConfiguration dndConfig = new UITreeDragDropConfiguration();
		dndConfig.addDropTarget(DatasourceFolderDto.class);
		tree.enableDragDrop(datasourceTreeHandler, dndConfig);
		tree.enableClipboardProvider();

		/* set selections */
		tree.setSelectionProvider(mainPanel);
		
		/* double click */
		tree.enableDoubleClickAction();
		
		/* history location */
		tree.setHistoryLocation(DatasourceUIModule.DATASOURCE_FAV_HISTORY_TOKEN);
		
		/* tree menu */
		tree.configureMenuProvider(DatasourceUIModule.ADMIN_TREE_MENU_NAME);
		
		return tree;
	}
}
