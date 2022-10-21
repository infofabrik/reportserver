package net.datenwerke.treedb.ext.client.eximport.im.dto;

import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportConfigDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class TreeImportConfigDto<N extends AbstractNodeDto> extends ImportConfigDto {

   /**
    * 
    */
   private static final long serialVersionUID = -4047383052789757623L;

   private N parent;

   public void setParent(N parent) {
      this.parent = parent;
   }

   public N getParent() {
      return parent;
   }

}
