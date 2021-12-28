package net.datenwerke.rs.grideditor.service.grideditor.definition.validator;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(dtoPackage = "net.datenwerke.rs.grideditor.client.grideditor.dto", generateDto2Poso = false, createDecorator = true)
public class MinIntegerValidator extends MinNumberValidator {

   @ExposeToClient
   private Integer number = 0;

   public MinIntegerValidator() {
   }

   public MinIntegerValidator(int number, String errorMsg) {
      setNumber(number);
      setErrorMsg(errorMsg);
   }

   public Integer getNumber() {
      return number;
   }

   public void setNumber(Integer number) {
      this.number = number;
   }

}
