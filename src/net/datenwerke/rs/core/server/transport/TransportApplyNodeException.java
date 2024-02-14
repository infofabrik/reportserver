package net.datenwerke.rs.core.server.transport;

import net.datenwerke.eximport.exceptions.ImportException;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporter.TreeItem;

public class TransportApplyNodeException extends ImportException {

   private static final long serialVersionUID = 2472777500013459225L;
   private TreeItem item = null;

   public TransportApplyNodeException() {
      super("Failed to apply Transport");
   }
   
   public TransportApplyNodeException(TreeItem item) {
      super("Failed to apply Transport");
      this.item = item;
   }
   
   public TransportApplyNodeException(TreeItem item, String msg) {
      super(msg);
      this.item = item;
   }
   
   public TransportApplyNodeException(TreeItem item, Exception e) {
      super("Failed to apply Transport", e);
      this.item = item;
   }
   
   public TransportApplyNodeException(TreeItem item, String msg, Exception e) {
      super(msg, e);
      this.item = item;
   }

   public TransportApplyNodeException(String msg) {
      super(msg);
   }

   public TransportApplyNodeException(String msg, Exception e) {
      super(msg, e);
   }
   
   public TreeItem getItem() {
      return item;
   }
}
