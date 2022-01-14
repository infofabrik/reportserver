package net.datenwerke.gf.client.treedb.dnd;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.core.client.util.Rectangle;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.dnd.core.client.DndDragMoveEvent;
import com.sencha.gxt.dnd.core.client.Insert;
import com.sencha.gxt.dnd.core.client.TreeDropTarget;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.sencha.gxt.widget.core.client.tree.Tree.TreeNode;

import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * Fixed TreePanelDropTarget to provide callbacks to allow/deny drop between and
 * on specific nodes.
 *
 */
public class UITreeDropTarget extends TreeDropTarget<AbstractNodeDto> {

   private boolean allowDropOnLeaf;
   private int autoExpandDelay = 800;
   private boolean autoExpand = true;

   public UITreeDropTarget(Tree<AbstractNodeDto, String> tree) {
      super(tree);
   }

   /**
    * Callback to be overriden
    * 
    * @param event
    * @param item
    */
   protected boolean allowDropOnNode(DndDragMoveEvent event, final TreeNode<AbstractNodeDto> item) {
      return true;
   }

   /**
    * Callback to be overridden
    * 
    * @param event
    * @param item
    * @param before
    * @param idx
    */
   protected boolean allowDropBetweenNode(DndDragMoveEvent event, TreeNode<AbstractNodeDto> item, boolean before,
         int idx) {
      return true;
   }

   @Override
   protected void handleAppend(DndDragMoveEvent event, final TreeNode<AbstractNodeDto> item) {
      if (!allowDropOnNode(event, item)) {
         if (activeItem != null)
            getWidget().getView().onDropChange(activeItem, false);
         event.setCancelled(true);
         event.getStatusProxy().setStatus(false);
         return;
      }

      // clear any active append item
      if (activeItem != null && activeItem != item) {
         getWidget().getView().onDropChange(activeItem, false);
      }
      status = -1;

      Insert.get().hide();
      event.getStatusProxy().setStatus(true);
      if (activeItem != null) {
         getWidget().getView().onDropChange(activeItem, false);
      }

      if (item != null && item != appendItem && autoExpand && !item.isExpanded()) {
         Timer t = new Timer() {
            @Override
            public void run() {
               if (item == appendItem) {
                  item.setExpanded(true);
               }
            }
         };
         t.schedule(autoExpandDelay);
      }
      appendItem = item;
      activeItem = item;
      if (activeItem != null) {
         getWidget().getView().onDropChange(activeItem, true);
      }
   }

   @Override
   protected void handleInsert(DndDragMoveEvent event, final TreeNode<AbstractNodeDto> item) {
      Element e = getWidget().getView().getElementContainer(item);

      // clear any active append item
      if (activeItem != null && activeItem != item) {
         getWidget().getView().onDropChange(activeItem, false);
      }

      int height = e.getOffsetHeight();
      int mid = height / 2;
      int top = e.getAbsoluteTop();
      mid += top;
      int y = event.getDragMoveEvent().getNativeEvent().getClientY();
      boolean before = y < mid;

      boolean leaf = getWidget().isLeaf(item.getModel());

      if (!leaf || allowDropOnLeaf) {
         if ((before && y > top + 4) || (!before && y < top + height - 4)) {
            handleAppend(event, item);
            return;
         }
      }

      if (event.getDropTarget().getWidget() == event.getDragSource().getWidget()) {
         Tree<AbstractNodeDto, String> source = (Tree<AbstractNodeDto, String>) event.getDragSource().getWidget();
         AbstractNodeDto selNode = source.getSelectionModel().getSelectedItem();
         AbstractNodeDto overNode = item.getModel();
         if (before && overNode == getWidget().getStore().getNextSibling(selNode)) {
            clearStyle(activeItem);
            return;
         }
      }

      appendItem = null;

      status = before ? 0 : 1;

      if (activeItem != null) {
         getWidget().getView().onDropChange(activeItem, false);
      }

      TreeStore<AbstractNodeDto> store = getWidget().getStore();
      activeItem = item;

      if (null == store.getParent(activeItem.getModel())) {
         event.setCancelled(true);
         event.getStatusProxy().setStatus(false);
         return;
      }

      /* get position */
      int idx = 0;
      AbstractNodeDto p = store.getParent(activeItem.getModel());
      if (p != null)
         idx = store.getChildren(p).indexOf(activeItem.getModel());
      else
         idx = store.getRootItems().indexOf(activeItem.getModel());

      /* hook */
      if (!allowDropBetweenNode(event, item, before, idx)) {
         if (activeItem != null)
            getWidget().getView().onDropChange(activeItem, false);
         event.setCancelled(true);
         event.getStatusProxy().setStatus(false);
         return;
      }

      ImageResource statusResource = resources.dropInsert();
      if (before && idx == 0) {
         statusResource = resources.dropInsertAbove();
      } else if (idx > 1 && !before && p != null && idx == store.getChildCount(p) - 1) {
         statusResource = resources.dropInsertBelow();
      }

      event.getStatusProxy().setStatus(true, statusResource);

      /* show insert */
      showInsert(event, e, before);
   }

   @Override
   public void setAllowDropOnLeaf(boolean allowDropOnLeaf) {
      this.allowDropOnLeaf = allowDropOnLeaf;
      super.setAllowDropOnLeaf(allowDropOnLeaf);
   }

   @Override
   public void setAutoExpand(boolean autoExpand) {
      this.autoExpand = autoExpand;
      super.setAutoExpand(autoExpand);
   }

   @Override
   public void setAutoExpandDelay(int autoExpandDelay) {
      this.autoExpandDelay = autoExpandDelay;
      super.setAutoExpandDelay(autoExpandDelay);
   }

   private void showInsert(DndDragMoveEvent event, Element elem, boolean before) {
      Insert insert = Insert.get();

      insert.show(elem);
      Rectangle rect = elem.<XElement>cast().getBounds();

      int y = before ? rect.getY() - 2 : (rect.getY() + rect.getHeight() - 4);

      // dont call setBounds though component as it expects widget to be attached
      insert.getElement().setBounds(rect.getX(), y, rect.getWidth(), 6);
   }
}