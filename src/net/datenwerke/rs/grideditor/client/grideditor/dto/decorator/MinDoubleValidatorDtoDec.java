package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.validator.MinNumberValidator;
import com.sencha.gxt.widget.core.client.form.validator.MinNumberValidator.MinNumberMessages;

import net.datenwerke.rs.grideditor.client.grideditor.dto.MinDoubleValidatorDto;

/**
 * Dto Decorator for {@link MinDoubleValidatorDto}
 *
 */
public class MinDoubleValidatorDtoDec extends MinDoubleValidatorDto {

   private static final long serialVersionUID = 1L;

   public MinDoubleValidatorDtoDec() {
      super();
   }

   @Override
   public Validator<?> getValidator() {
      MinNumberValidator<Double> validator = new MinNumberValidator<Double>(getNumber());
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
