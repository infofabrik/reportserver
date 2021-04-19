package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import net.datenwerke.rs.grideditor.client.grideditor.dto.MaxLengthValidatorDto;

import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.validator.MaxLengthValidator;
import com.sencha.gxt.widget.core.client.form.validator.MaxLengthValidator.MaxLengthMessages;

/**
 * Dto Decorator for {@link MaxLengthValidatorDto}
 *
 */
public class MaxLengthValidatorDtoDec extends MaxLengthValidatorDto {


	private static final long serialVersionUID = 1L;

	public MaxLengthValidatorDtoDec() {
		super();
	}


	@Override
	public Validator<?> getValidator() {
		MaxLengthValidator validator = new MaxLengthValidator(getLength());
		validator.setMessages(new MaxLengthMessages() {
			@Override
			public String maxLengthText(int length) {
				return getErrorMsg();
			}
		});
		return validator;
	}
}
