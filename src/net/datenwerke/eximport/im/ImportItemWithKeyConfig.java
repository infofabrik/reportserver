package net.datenwerke.eximport.im;

import net.datenwerke.treedb.ext.service.eximport.TreeNodeImportItemConfig;

public class ImportItemWithKeyConfig extends TreeNodeImportItemConfig {

   private boolean cleanKeys = false;

   public ImportItemWithKeyConfig(String id) {
      super(id);
   }

   public void setCleanKeys(boolean cleanKeys) {
      this.cleanKeys = cleanKeys;
   }

   public boolean isCleanKeys() {
      return cleanKeys;
   }
}
