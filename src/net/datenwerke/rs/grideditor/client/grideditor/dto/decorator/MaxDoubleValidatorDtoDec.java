package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import net.datenwerke.rs.grideditor.client.grideditor.dto.MaxDoubleValidatorDto;

import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.validator.MaxNumberValidator;
import com.sencha.gxt.widget.core.client.form.validator.MaxNumberValidator.MaxNumberMessages;

/**
 * Dto Decorator for {@link MaxDoubleValidatorDto}
 *
 */
public class MaxDoubleValidatorDtoDec extends MaxDoubleValidatorDto {


	private static final long serialVersionUID = 1L;

	public MaxDoubleValidatorDtoDec() {
		super();
	}

	@Override
	public Validator<?> getValidator() {
		MaxNumberValidator<Double> validator = new MaxNumberValidator<Double>(getNumber());
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
