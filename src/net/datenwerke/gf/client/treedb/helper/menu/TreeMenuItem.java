package net.datenwerke.gf.client.treedb.helper.menu;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.widget.core.client.menu.Item;


public class TreeMenuItem extends DwMenuItem {

	final protected List<TreeMenuSelectionEvent> eventListeners = new ArrayList<TreeMenuSelectionEvent>();
	protected UITree tree;

	public TreeMenuItem(){
		super();
		
		/* add selection listener */
		addSelectionHandler(new SelectionHandler<Item>() {
			
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				/* find node */
				if(null == tree)
					return;
				
				//GenericNodeDTO node = tree.getSelectionModel().getSelectedItem();
				AbstractNodeDto node = tree.getContextMenuNode();
				
				/* inform listeners */
				if(null != node)
					for(TreeMenuSelectionEvent listener : eventListeners)
						listener.menuItemSelected(tree, node);
			}
		});
	}
	
	/**
	 * Adds a listener to this menu item
	 */
	public void addMenuSelectionListener(
			TreeMenuSelectionEvent listener) {
		eventListeners.add(listener);
	}

	public void setTree(UITree tree) {
		this.tree = tree;
	}
	

	public void toBeDisplayed(AbstractNodeDto node) {
	}

}
