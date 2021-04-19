package net.datenwerke.gf.client.managerhelper.hooks;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public interface MainPanelViewToolbarConfiguratorHook extends Hook {

	public void mainPanelViewToolbarConfiguratorHook_addLeft(MainPanelView view, ToolBar toolbar, AbstractNodeDto selectedNode);
	
	public void mainPanelViewToolbarConfiguratorHook_addRight(MainPanelView view, ToolBar toolbar, AbstractNodeDto selectedNode);
	
}
