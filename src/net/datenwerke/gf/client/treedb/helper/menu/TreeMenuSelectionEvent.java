package net.datenwerke.gf.client.treedb.helper.menu;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public interface TreeMenuSelectionEvent {

   public void menuItemSelected(UITree tree, AbstractNodeDto node);
}
