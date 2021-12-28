package net.datenwerke.rs.grideditor.service.grideditor.definition.editor;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(dtoPackage = "net.datenwerke.rs.grideditor.client.grideditor.dto", generateDto2Poso = false, createDecorator = true)
public class IntBooleanEditor extends Editor {

   @ExposeToClient
   private int trueInt = 1;

   @ExposeToClient
   private int falseInt = 0;

   public int getTrueInt() {
      return trueInt;
   }

   public void setTrueInt(int trueInt) {
      this.trueInt = trueInt;
   }

   public int getFalseInt() {
      return falseInt;
   }

   public void setFalseInt(int falseInt) {
      this.falseInt = falseInt;
   }

}
