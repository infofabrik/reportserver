package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.validator.MinNumberValidator;
import com.sencha.gxt.widget.core.client.form.validator.MinNumberValidator.MinNumberMessages;

import net.datenwerke.rs.grideditor.client.grideditor.dto.MinFloatValidatorDto;

/**
 * Dto Decorator for {@link MinFloatValidatorDto}
 *
 */
public class MinFloatValidatorDtoDec extends MinFloatValidatorDto {

   private static final long serialVersionUID = 1L;

   public MinFloatValidatorDtoDec() {
      super();
   }

   @Override
   public Validator<?> getValidator() {
      MinNumberValidator<Float> validator = new MinNumberValidator<Float>(getNumber());
      validator.setMessages(new MinNumberMessages() {
         @Override
         public String numberMinText(double min) {
            return getErrorMsg();
         }

         @Override
         public String numberMinText(String formattedValue) {
            return getErrorMsg();
         }
      });
      return validator;
   }
}
