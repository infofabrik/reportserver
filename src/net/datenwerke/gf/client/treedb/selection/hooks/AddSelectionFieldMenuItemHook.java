package net.datenwerke.gf.client.treedb.selection.hooks;

import com.sencha.gxt.widget.core.client.menu.Menu;

import net.datenwerke.gf.client.treedb.selection.SingleTreeSelectionField;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public interface AddSelectionFieldMenuItemHook extends Hook {

	public interface MenuNodeProvider{
		public AbstractNodeDto getNode();
	}
	
	public void addMenuEntries(SingleTreeSelectionField singleTreeSelectionField, Menu menu, MenuNodeProvider callback);
	
}
