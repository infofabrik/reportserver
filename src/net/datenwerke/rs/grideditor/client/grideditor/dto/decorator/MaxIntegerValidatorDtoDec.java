package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.validator.MaxNumberValidator;
import com.sencha.gxt.widget.core.client.form.validator.MaxNumberValidator.MaxNumberMessages;

import net.datenwerke.rs.grideditor.client.grideditor.dto.MaxIntegerValidatorDto;

/**
 * Dto Decorator for {@link MaxIntegerValidatorDto}
 *
 */
public class MaxIntegerValidatorDtoDec extends MaxIntegerValidatorDto {

   private static final long serialVersionUID = 1L;

   public MaxIntegerValidatorDtoDec() {
      super();
   }

   @Override
   public Validator<?> getValidator() {
      MaxNumberValidator<Integer> validator = new MaxNumberValidator<Integer>(getNumber());
      validator.setMessages(new MaxNumberMessages() {
         @Override
         public String numberMaxText(double max) {
            return getErrorMsg();
         }

         @Override
         public String numberMaxText(String formattedValue) {
            return getErrorMsg();
         }
      });
      return validator;
   }
}
