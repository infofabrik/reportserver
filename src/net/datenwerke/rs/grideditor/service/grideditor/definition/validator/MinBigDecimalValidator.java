package net.datenwerke.rs.grideditor.service.grideditor.definition.validator;

import java.math.BigDecimal;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(dtoPackage = "net.datenwerke.rs.grideditor.client.grideditor.dto", generateDto2Poso = false, createDecorator = true)
public class MinBigDecimalValidator extends MinNumberValidator {

   @ExposeToClient
   private BigDecimal number;

   public MinBigDecimalValidator() {
   }

   public MinBigDecimalValidator(BigDecimal number, String errorMsg) {
      setNumber(number);
      setErrorMsg(errorMsg);
   }

   public BigDecimal getNumber() {
      return number;
   }

   public void setNumber(BigDecimal number) {
      this.number = number;
   }

}
