package net.datenwerke.gf.client.managerhelper.hooks;

import com.google.gwt.event.dom.client.DoubleClickEvent;

import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.treedb.helper.menu.TreeDBUIMenuProvider;
import net.datenwerke.gf.client.treedb.icon.TreeDBUIIconProvider;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public interface TreeConfiguratorHook extends Hook {

   boolean consumes(ManagerHelperTree tree);

   void configureTreeIcons(TreeDBUIIconProvider iconProvider);

   void configureTreeMenu(TreeDBUIMenuProvider menuProvider);

   void onDoubleClick(AbstractNodeDto selectedItem, DoubleClickEvent event);

   void configureFolderTypes(ManagerHelperTree managerHelperTree);

}
