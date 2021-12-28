package net.datenwerke.treedb.ext.client.eximport.im.dto;

import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportItemConfigDto;

public class TreeImportNodeConfigDto extends ImportItemConfigDto {

   /**
    * 
    */
   private static final long serialVersionUID = -7167010993756342938L;

   private ImportTreeModel model;

   public TreeImportNodeConfigDto() {
   }

   public TreeImportNodeConfigDto(ImportTreeModel model) {
      setModel(model);
   }

   public void setModel(ImportTreeModel model) {
      this.model = model;
   }

   public ImportTreeModel getModel() {
      return model;
   }
}
