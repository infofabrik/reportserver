package net.datenwerke.eximport.im.entity;

import net.datenwerke.eximport.im.ImportItemConfig;
import net.datenwerke.eximport.im.ImportMode;

public class EntityImportItemConfig extends ImportItemConfig {

   public EntityImportItemConfig(String id) {
      super(id);
   }

   public EntityImportItemConfig(String id, ImportMode importMode) {
      super(id, importMode);
   }

   public EntityImportItemConfig(String id, ImportMode importMode, Object referenceObject) {
      super(id, importMode, referenceObject);
   }
}
