package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import net.datenwerke.rs.grideditor.client.grideditor.dto.MaxLongValidatorDto;

import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.validator.MaxNumberValidator;
import com.sencha.gxt.widget.core.client.form.validator.MaxNumberValidator.MaxNumberMessages;

/**
 * Dto Decorator for {@link MaxLongValidatorDto}
 *
 */
public class MaxLongValidatorDtoDec extends MaxLongValidatorDto {


	private static final long serialVersionUID = 1L;

	public MaxLongValidatorDtoDec() {
		super();
	}

	@Override
	public Validator<?> getValidator() {
		MaxNumberValidator<Long> validator = new MaxNumberValidator<Long>(getNumber());
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
