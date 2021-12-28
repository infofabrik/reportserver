package net.datenwerke.rs.grideditor.service.grideditor.definition.validator;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(dtoPackage = "net.datenwerke.rs.grideditor.client.grideditor.dto", generateDto2Poso = false, createDecorator = true)
public class MaxIntegerValidator extends MaxNumberValidator {

   @ExposeToClient
   private Integer number = 0;

   public MaxIntegerValidator() {
   }

   public MaxIntegerValidator(int number, String errorMsg) {
      setErrorMsg(errorMsg);
      setNumber(number);
   }

   public Integer getNumber() {
      return number;
   }

   public void setNumber(Integer number) {
      this.number = number;
   }

}
