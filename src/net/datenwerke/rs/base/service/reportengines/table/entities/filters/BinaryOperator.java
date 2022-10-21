package net.datenwerke.rs.base.service.reportengines.table.entities.filters;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(dtoPackage = "net.datenwerke.rs.base.client.reportengines.table.dto")
public enum BinaryOperator {

   LESS("<"), LESS_OR_EQUALS("<="), EQUALS("=="), NOT_EQUALS("!="), GREATER(">"), GREATER_OR_EQUALS(">=");

   private String strOp;

   BinaryOperator(String strOp) {
      this.strOp = strOp;
   }

   public String getStrOp() {
      return strOp;
   }

}
