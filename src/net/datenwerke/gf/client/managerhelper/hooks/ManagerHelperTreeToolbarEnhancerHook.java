package net.datenwerke.gf.client.managerhelper.hooks;

import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.treedb.client.treedb.TreeDbManagerContainer;

public interface ManagerHelperTreeToolbarEnhancerHook extends Hook {

	public void treeNavigationToolbarEnhancerHook_addLeft(ToolBar toolbar, UITree tree, TreeDbManagerContainer treeManagerContainer);
	
	public void treeNavigationToolbarEnhancerHook_addRight(ToolBar toolbar, UITree tree, TreeDbManagerContainer treeManagerContainer);

}
