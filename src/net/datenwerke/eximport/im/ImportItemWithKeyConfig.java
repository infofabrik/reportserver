package net.datenwerke.eximport.im;

import net.datenwerke.treedb.ext.service.eximport.TreeNodeImportItemConfig;

public class ImportItemWithKeyConfig extends TreeNodeImportItemConfig {

   private boolean randomKeys = false;

   public ImportItemWithKeyConfig(String id) {
      super(id);
   }

   public void setCreateRandomKeys(boolean randomKeys) {
      this.randomKeys = randomKeys;
   }

   public boolean isCreateRandomKeys() {
      return randomKeys;
   }
}
