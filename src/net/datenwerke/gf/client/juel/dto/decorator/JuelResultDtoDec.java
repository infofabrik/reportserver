package net.datenwerke.gf.client.juel.dto.decorator;

import net.datenwerke.gf.client.juel.dto.JuelResultDto;

/**
 * Dto Decorator for {@link JuelResultDto}
 *
 */
public class JuelResultDtoDec extends JuelResultDto {

   private static final long serialVersionUID = 1L;

   public JuelResultDtoDec() {
      super();
   }

   public Object getValue() {
      if (isEntryNull())
         return null;

      switch (getType()) {
      case BOOLEAN:
         return isBooleanValue();
      case STRING:
         return getStringValue();
      case LONG:
         return getLongValue();
      case INTEGER:
         return getIntValue();
      case DECIMAL:
         return getDecimalValue();
      case DATE:
         return getDateValue();
      case DOUBLE:
         return getDoubleValue();
      case FLOAT:
         return getFloatValue();
      }

      return null;
   }
}
