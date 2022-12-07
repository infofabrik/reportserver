package net.datenwerke.treedb.service.treedb.exceptions;

import net.datenwerke.treedb.service.treedb.AbstractNode;
import net.datenwerke.treedb.service.treedb.locale.TreeDbMessages;

public class UnsupportedChildException extends TreeDBRuntimeException {

   /**
    * 
    */
   private static final long serialVersionUID = -5119598342580086538L;

   public UnsupportedChildException(AbstractNode<?> parent, AbstractNode<?> child) {
      super(TreeDbMessages.INSTANCE.exceptionUnsupportedChild(getNodeInfo(child),
            getNodeInfo(parent)));
   }
   
   private static String getNodeInfo(AbstractNode<?> node) {
      StringBuilder sb = new StringBuilder();
      sb.append("(class: ")
         .append(node.getClass().getSimpleName())
         .append(", ")
         .append("id: ")
         .append(node.getId())
         .append(", ")
         .append("name: ")
         .append(node.getNodeName())
         .append(")");
      return sb.toString();
   }

}
