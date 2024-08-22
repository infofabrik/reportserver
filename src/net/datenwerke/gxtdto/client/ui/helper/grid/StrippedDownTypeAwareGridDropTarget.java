package net.datenwerke.gxtdto.client.ui.helper.grid;

import java.util.ArrayList;
import java.util.List;

import com.sencha.gxt.data.shared.TreeStore.TreeNode;
import com.sencha.gxt.dnd.core.client.DndDragMoveEvent;
import com.sencha.gxt.widget.core.client.grid.Grid;

import net.datenwerke.security.client.usermanager.dto.GroupDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.ie.AbstractStrippedDownNode;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownGroup;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;

public class StrippedDownTypeAwareGridDropTarget<M> extends TypeAwareGridDropTarget<M> {

   private final Class<? extends AbstractStrippedDownNode> strippedDownType;

   public StrippedDownTypeAwareGridDropTarget(Grid<M> grid, Class<? extends AbstractStrippedDownNode> strippedDownType,
         Class<?>... types) {
      super(grid, types);

      this.strippedDownType = strippedDownType;
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
         if (strippedDownType.equals(data.getClass())) {
            foundType = true;
         }

         if (!foundType) {
            for (Class<?> type : types) {
               if (type.equals(data.getClass())) {
                  foundType = true;
                  break;
               }
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
               Object treeNodeData = ((TreeNode) obj).getData();
               if (treeNodeData instanceof AbstractStrippedDownNode) {
                  models.add(treeNodeData);
               } else {
                  Object strippedDownObject = getStrippedDownObject(treeNodeData);
                  models.add(strippedDownObject);
               }
            }
         }
      }

      return models;
   }

   private Object getStrippedDownObject(Object treeNodeData) {
      if (treeNodeData instanceof UserDto)
         return StrippedDownUser.fromUser((UserDto) treeNodeData);
      else if (treeNodeData instanceof GroupDto)
         return StrippedDownGroup.fromGroup((GroupDto) treeNodeData);
      return null;
   }
}
