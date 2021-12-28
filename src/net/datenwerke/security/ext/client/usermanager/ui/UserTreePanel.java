package net.datenwerke.security.ext.client.usermanager.ui;

import com.google.inject.Inject;

import net.datenwerke.gf.client.managerhelper.ui.AbstractTreeNavigationPanel;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.security.ext.client.usermanager.provider.annotations.UserManagerAdminViewTree;

/**
 * 
 *
 */
public class UserTreePanel extends AbstractTreeNavigationPanel {

	@Inject
	public UserTreePanel(
		HookHandlerService hookHandler,	
		@UserManagerAdminViewTree UITree tree){
		super(hookHandler, tree);
	}
	
}
