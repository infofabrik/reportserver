package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import net.datenwerke.rs.grideditor.client.grideditor.dto.MinIntegerValidatorDto;

import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.validator.MinNumberValidator;
import com.sencha.gxt.widget.core.client.form.validator.MinNumberValidator.MinNumberMessages;

/**
 * Dto Decorator for {@link MinIntegerValidatorDto}
 *
 */
public class MinIntegerValidatorDtoDec extends MinIntegerValidatorDto {


	private static final long serialVersionUID = 1L;

	public MinIntegerValidatorDtoDec() {
		super();
	}

	@Override
	public Validator<?> getValidator() {
		MinNumberValidator<Integer> validator = new MinNumberValidator<Integer>(getNumber());
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
