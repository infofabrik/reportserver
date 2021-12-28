package net.datenwerke.rs.grideditor.service.grideditor.definition.validator;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(dtoPackage = "net.datenwerke.rs.grideditor.client.grideditor.dto", generateDto2Poso = false, createDecorator = true)
public class MinDoubleValidator extends MinNumberValidator {

   @ExposeToClient
   private Double number = 0d;

   public MinDoubleValidator() {
   }

   public MinDoubleValidator(double number, String errorMsg) {
      setNumber(number);
      setErrorMsg(errorMsg);
   }

   public Double getNumber() {
      return number;
   }

   public void setNumber(Double number) {
      this.number = number;
   }

}
