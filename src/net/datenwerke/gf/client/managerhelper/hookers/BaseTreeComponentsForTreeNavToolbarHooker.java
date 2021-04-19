package net.datenwerke.gf.client.managerhelper.hookers;

import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gf.client.managerhelper.hooks.ManagerHelperTreeToolbarEnhancerHook;
import net.datenwerke.gf.client.managerhelper.hooks.TreeSelectionToolbarEnhancerHook;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.TreeDbManagerContainer;

public class BaseTreeComponentsForTreeNavToolbarHooker implements
		ManagerHelperTreeToolbarEnhancerHook, TreeSelectionToolbarEnhancerHook {
	
	@Override
	public void treeNavigationToolbarEnhancerHook_addLeft(ToolBar toolbar,
			UITree tree, TreeDbManagerContainer treeManagerContainer) {
	}
	
	@Override
	public void treeNavigationToolbarEnhancerHook_addRight(ToolBar toolbar, final UITree tree, TreeDbManagerContainer treeManagerContainer) {
		/* expand all button */
		DwTextButton expandAllButton = new DwTextButton(BaseIcon.EXPAND_ALL);
		expandAllButton.setToolTip(BaseMessages.INSTANCE.expandAll());
		expandAllButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				tree.expandAll();
			}
		});
		toolbar.add(expandAllButton);

		/* collapse all button */
		DwTextButton collapseAllButton = new DwTextButton(BaseIcon.COLLAPSE_ALL);
		collapseAllButton.setToolTip(BaseMessages.INSTANCE.collapseAll());
		collapseAllButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				tree.collapseAll();
			}
		});
		toolbar.add(collapseAllButton);

		/* reload button */
		DwTextButton reloadButton = new DwTextButton(BaseIcon.REFRESH);
		reloadButton.setToolTip(BaseMessages.INSTANCE.refresh());
		reloadButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				tree.reload();
			}
		});
		toolbar.add(reloadButton);
	}

}
