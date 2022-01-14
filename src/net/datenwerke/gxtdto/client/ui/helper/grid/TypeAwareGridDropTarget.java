package net.datenwerke.gxtdto.client.ui.helper.grid;

import java.util.ArrayList;
import java.util.List;

import com.sencha.gxt.data.shared.TreeStore.TreeNode;
import com.sencha.gxt.dnd.core.client.DndDragMoveEvent;
import com.sencha.gxt.dnd.core.client.GridDropTarget;
import com.sencha.gxt.widget.core.client.grid.Grid;

/**
 * 
 *
 */
public class TypeAwareGridDropTarget<M> extends GridDropTarget<M> {

   protected final Class<?>[] types;

   public TypeAwareGridDropTarget(Grid<M> grid, Class<?>... types) {
      super(grid);

      this.types = types;
   }

   @Override
   protected void onDragMove(DndDragMoveEvent event) {
      List list = (List) event.getData();
      if (list.size() > 0) {
         Object m = list.get(0);

         /* search for object */
         Object data = m;
         if (m instanceof TreeNode)
            data = ((TreeNode) m).getData();

         boolean foundType = false;
         for (Class<?> type : types) {
            if (type.equals(data.getClass())) {
               foundType = true;
               break;
            }
         }

         if (foundType) {
            super.onDragMove(event);
         } else {
            event.setCancelled(true);
            event.getStatusProxy().setStatus(false);
            return;
         }
      }
   }

   @Override
   protected List<Object> prepareDropData(Object data, boolean convertTreeStoreModel) {
      List<Object> models = new ArrayList<Object>();

      if (data instanceof TreeNode) {
         models.add(((TreeNode) data).getData());
      } else if (data instanceof List) {
         for (Object obj : (List) data) {
            if (obj instanceof TreeNode) {
               models.add(((TreeNode) obj).getData());
            }
         }
      }

      return models;
   }

}
