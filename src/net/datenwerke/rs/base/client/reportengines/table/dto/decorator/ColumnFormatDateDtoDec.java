package net.datenwerke.rs.base.client.reportengines.table.dto.decorator;

import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDateDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDto;

/**
 * Dto Decorator for {@link ColumnFormatDateDto}
 *
 */
public class ColumnFormatDateDtoDec extends ColumnFormatDateDto {

   private static final long serialVersionUID = 1L;

   public ColumnFormatDateDtoDec() {
      super();
   }

   @Override
   public ColumnFormatDto cloneFormat() {
      ColumnFormatDateDtoDec clone = new ColumnFormatDateDtoDec();

      clone.setBaseFormat(getBaseFormat());
      clone.setTargetFormat(getTargetFormat());
      clone.setErrorReplacement(getErrorReplacement());
      clone.setReplaceErrors(isReplaceErrors());
      clone.setRollOver(isRollOver());

      return clone;
   }

}
