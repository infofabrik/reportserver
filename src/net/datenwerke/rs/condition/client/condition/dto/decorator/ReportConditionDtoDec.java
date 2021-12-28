package net.datenwerke.rs.condition.client.condition.dto.decorator;

import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.rs.condition.client.condition.Condition;
import net.datenwerke.rs.condition.client.condition.dto.ReportConditionDto;

/**
 * Dto Decorator for {@link ReportConditionDto}
 *
 */
public class ReportConditionDtoDec extends ReportConditionDto implements Condition, IdedDto {

   private static final long serialVersionUID = 1L;

   public ReportConditionDtoDec() {
      super();
   }

   @Override
   public boolean hasExpression() {
      return true;
   }

}