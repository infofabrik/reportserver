package net.datenwerke.rs.grideditor.service.grideditor.definition.validator;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(dtoPackage = "net.datenwerke.rs.grideditor.client.grideditor.dto", generateDto2Poso = false, createDecorator = true)
public class MinLongValidator extends MinNumberValidator {

   @ExposeToClient
   private Long number;

   public MinLongValidator() {
   }

   public MinLongValidator(long number, String errorMsg) {
      setNumber(number);
      setErrorMsg(errorMsg);
   }

   public Long getNumber() {
      return number;
   }

   public void setNumber(Long number) {
      this.number = number;
   }

}
