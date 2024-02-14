package net.datenwerke.rs.transport.service.transport.logs;

import javax.annotation.Nullable;

import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporter.TreeItem;

public class TransportApplyErrorLog extends TransportApplyLog {

   @Nullable
   private final String[] stackTrace;
   @Nullable
   private TreeItem item;

   public TransportApplyErrorLog(@Nullable Long timestamp, String message, @Nullable String[] stackTrace, @Nullable TreeItem item) {
      super(timestamp, message);
      this.stackTrace = stackTrace;
      this.item = item;
   }
   
   public void setItem(TreeItem item) {
      this.item = item;
   }
   
   @Nullable
   public String[] getStackTrace() {
      return stackTrace;
   }
   
   @Nullable
   public TreeItem getItem() {
      return item;
   }
}
