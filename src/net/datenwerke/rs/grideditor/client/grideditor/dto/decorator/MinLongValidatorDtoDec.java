package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.validator.MinNumberValidator;
import com.sencha.gxt.widget.core.client.form.validator.MinNumberValidator.MinNumberMessages;

import net.datenwerke.rs.grideditor.client.grideditor.dto.MinLongValidatorDto;

/**
 * Dto Decorator for {@link MinLongValidatorDto}
 *
 */
public class MinLongValidatorDtoDec extends MinLongValidatorDto {


	private static final long serialVersionUID = 1L;

	public MinLongValidatorDtoDec() {
		super();
	}

	@Override
	public Validator<?> getValidator() {
		MinNumberValidator<Long> validator = new MinNumberValidator<Long>(getNumber());
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
