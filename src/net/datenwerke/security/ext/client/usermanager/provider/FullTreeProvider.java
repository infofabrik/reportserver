package net.datenwerke.security.ext.client.usermanager.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.dnd.UITreeDragDropConfiguration;
import net.datenwerke.security.client.usermanager.dto.GroupDto;
import net.datenwerke.security.client.usermanager.dto.OrganisationalUnitDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.ext.client.usermanager.UserManagerTreeManagerDao;
import net.datenwerke.security.ext.client.usermanager.UserManagerUIModule;
import net.datenwerke.security.ext.client.usermanager.ui.UserMainPanel;

/**
 * Provides the user manager tree with all goodies.
 *
 */
public class FullTreeProvider implements Provider<UITree>{

	private final BasicTreeProvider basicTreeProvider;
	private final UserManagerTreeManagerDao userManagerTreeHandler;
	private final UserMainPanel mainPanel;

	private ManagerHelperTree managerHelperTree;
	
	@Inject
	public FullTreeProvider(
			BasicTreeProvider basicTreeProvider,
			UserManagerTreeManagerDao userManagerTreeHandler,
			UserMainPanel mainPanel 
		){
		
		this.basicTreeProvider = basicTreeProvider;
		this.userManagerTreeHandler = userManagerTreeHandler;
		this.mainPanel = mainPanel;
	}
	


	private ManagerHelperTree getTree(){
		if(null == managerHelperTree){
			managerHelperTree = basicTreeProvider.get();
		}
		return managerHelperTree;
	}
	
	public UITree get() {
		/* build tree */
		final ManagerHelperTree tree = getTree();
		tree.getStore().enableDtoAwareness(true);
		
		/* dnd */
		UITreeDragDropConfiguration dndConfig = new UITreeDragDropConfiguration();
		dndConfig.addDropTarget(OrganisationalUnitDto.class, GroupDto.class, UserDto.class, OrganisationalUnitDto.class);
		tree.enableDragDrop(userManagerTreeHandler, dndConfig);
		tree.enableClipboardProvider();
		
		/* set selections */
		tree.setSelectionProvider(mainPanel);
		
		/* double click */
		tree.enableDoubleClickAction();
		
		/* history location */
		tree.setHistoryLocation(UserManagerUIModule.USERMANAGER_FAV_HISTORY_TOKEN);
		
		/* tree menu */
		tree.configureMenuProvider(UserManagerUIModule.ADMIN_TREE_MENU_NAME);
		
		return tree;
	}
}
