package net.datenwerke.treedb.ext.service.eximport;

import com.google.common.base.MoreObjects;

import net.datenwerke.eximport.ex.ExportItemConfig;
import net.datenwerke.treedb.service.treedb.AbstractNode;

/**
 * 
 *
 */
public class TreeNodeExportItemConfig extends ExportItemConfig<AbstractNode<?>> {

   public TreeNodeExportItemConfig(AbstractNode<?> node) {
      super(node);
      if (null == node.getId())
         throw new IllegalArgumentException(
               "Exportable nodes have to have an Id. " + node.getClass().getName() + " did not have an Id.");
   }
   
   public AbstractNode<?> getNode() {
      return getItem();
   }

   @Override
   public String toString() {
      return MoreObjects.toStringHelper(getClass())
         .add("id", id)
         .add("item", item)
         .toString();
   }
}
