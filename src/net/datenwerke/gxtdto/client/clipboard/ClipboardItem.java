package net.datenwerke.gxtdto.client.clipboard;

import java.io.Serializable;

public abstract class ClipboardItem implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = -1526237611233936131L;

   private Class<?> type;

   public ClipboardItem() {
   }

   public ClipboardItem(Class<?> type) {
      this.type = type;
   }

   public Class<?> getType() {
      return type;
   }

   public void setType(Class<?> type) {
      this.type = type;
   }

}
