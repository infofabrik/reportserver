package net.datenwerke.rs.grideditor.service.grideditor.definition.validator;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(dtoPackage = "net.datenwerke.rs.grideditor.client.grideditor.dto", generateDto2Poso = false, createDecorator = true)
public class MaxFloatValidator extends MaxNumberValidator {

   @ExposeToClient
   private Float number = 0f;

   public MaxFloatValidator() {
   }

   public MaxFloatValidator(Float number, String errorMsg) {
      setErrorMsg(errorMsg);
      setNumber(number);
   }

   public Float getNumber() {
      return number;
   }

   public void setNumber(Float number) {
      this.number = number;
   }

}
