package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.validator.MaxNumberValidator;
import com.sencha.gxt.widget.core.client.form.validator.MaxNumberValidator.MaxNumberMessages;

import net.datenwerke.rs.grideditor.client.grideditor.dto.MaxFloatValidatorDto;

/**
 * Dto Decorator for {@link MaxFloatValidatorDto}
 *
 */
public class MaxFloatValidatorDtoDec extends MaxFloatValidatorDto {


	private static final long serialVersionUID = 1L;

	public MaxFloatValidatorDtoDec() {
		super();
	}

	@Override
	public Validator<?> getValidator() {
		MaxNumberValidator<Float> validator = new MaxNumberValidator<Float>(getNumber());
		validator.setMessages(new MaxNumberMessages() {
			@Override
			public String numberMaxText(double min) {
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
