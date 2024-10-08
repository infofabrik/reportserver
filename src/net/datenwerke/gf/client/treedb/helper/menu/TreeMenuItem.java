package net.datenwerke.gf.client.treedb.helper.menu;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class TreeMenuItem extends DwMenuItem {

   final protected List<TreeMenuSelectionEvent> eventListeners = new ArrayList<TreeMenuSelectionEvent>();
   protected UITree tree;

   public TreeMenuItem() {
      super();

      /* add selection listener */
      addSelectionHandler(event -> {
         /* find node */
         if (null == tree)
            return;

         // GenericNodeDTO node = tree.getSelectionModel().getSelectedItem();
         final AbstractNodeDto node = tree.getContextMenuNode();

         /* inform listeners */
         if (null != node)
            eventListeners.forEach(listener -> listener.menuItemSelected(tree, node));
      });
   }

   /**
    * Adds a listener to this menu item
    */
   public void addMenuSelectionListener(TreeMenuSelectionEvent listener) {
      eventListeners.add(listener);
   }

   public void setTree(UITree tree) {
      this.tree = tree;
   }

   public void toBeDisplayed(AbstractNodeDto node) {
   }

}
