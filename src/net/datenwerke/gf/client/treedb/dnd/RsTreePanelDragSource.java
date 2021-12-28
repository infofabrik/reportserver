package net.datenwerke.gf.client.treedb.dnd;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Element;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.core.client.util.Format;
import com.sencha.gxt.data.shared.TreeStore.TreeNode;
import com.sencha.gxt.dnd.core.client.DND.Operation;
import com.sencha.gxt.dnd.core.client.DND.TreeSource;
import com.sencha.gxt.dnd.core.client.DndDragStartEvent;
import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.dnd.core.client.TreeDragSource;
import com.sencha.gxt.widget.core.client.tree.Tree;

/**
 * <code>DragSource</code> implementation for TreePanel.
 * 
 * Just like the default gxt TreePanelDragSource but does not add the mousedown
 * listener in IE
 */

public class RsTreePanelDragSource<M> extends TreeDragSource<M> {

   protected Tree<M, String> tree;
   protected TreeSource treeSource = TreeSource.BOTH;

   public RsTreePanelDragSource(Tree<M, String> tree) {
      super(tree);
      this.tree = tree;
      setStatusText("{0} items selected");

      if (!GXT.isIE()) {
         tree.addDomHandler(new MouseDownHandler() {

            @Override
            public void onMouseDown(MouseDownEvent event) {
               RsTreePanelDragSource.this.tree.focus();
            }
         }, MouseDownEvent.getType());
      }
   }

   /**
    * Returns the type of items that can be dragged.
    * 
    * @return the tree source type
    */
   public TreeSource getTreeSource() {
      return treeSource;
   }

   /**
    * Sets which tree items can be dragged (defaults to BOTH).
    * 
    * @param treeSource the tree source type
    */
   public void setTreeSource(TreeSource treeSource) {
      this.treeSource = treeSource;
   }

   @Override
   protected void onDragDrop(DndDropEvent event) {
      if (event.getOperation() == Operation.MOVE) {
         List<TreeNode<M>> sel = (List<TreeNode<M>>) event.getData();
         for (TreeNode<M> s : sel)
            tree.getStore().remove(s.getData());
      }
   }

   @Override
   protected void onDragStart(DndDragStartEvent event) {
      Element startTarget = event.getDragStartEvent().getStartElement().<Element>cast();
      Tree.TreeNode<M> start = tree.findNode(startTarget);
      if (start == null) {
         event.setCancelled(true);
         return;
      }

      M m = start.getModel();
      if (!tree.getView().isSelectableTarget(m, startTarget)) {
         event.setCancelled(true);
         return;
      }

      boolean leaf = treeSource == TreeSource.LEAF || treeSource == TreeSource.BOTH;
      boolean node = treeSource == TreeSource.NODE || treeSource == TreeSource.BOTH;

      List<M> sel = getWidget().getSelectionModel().getSelectedItems();

      if (sel.size() == 0) {
         event.setCancelled(true);
         return;
      }

      if (sel.size() > 0) {
         boolean ok = true;
         for (M mi : sel) {
            if ((leaf && tree.isLeaf(mi)) || (node && !tree.isLeaf(mi))) {
               continue;
            }
            ok = false;
            break;
         }
         if (ok) {
            List models = new ArrayList();
            for (M mi : sel)
               models.add(tree.getStore().getSubTree(mi));

            event.setData(models);
            event.setCancelled(false);
            event.getStatusProxy()
                  .update(SafeHtmlUtils.fromTrustedString(Format.substitute(getStatusText(), sel.size())));

         } else {
            event.setCancelled(true);
         }
      } else {
         event.setCancelled(true);
      }
   }

}
