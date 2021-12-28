package net.datenwerke.rs.grideditor.service.grideditor.definition.editor;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(dtoPackage = "net.datenwerke.rs.grideditor.client.grideditor.dto", generateDto2Poso = false, abstractDto = true)
public abstract class SelectionListEditor extends Editor {

   @ExposeToClient
   private boolean forceSelection = true;

   public boolean isForceSelection() {
      return forceSelection;
   }

   public void setForceSelection(boolean forceSelection) {
      this.forceSelection = forceSelection;
   }

}