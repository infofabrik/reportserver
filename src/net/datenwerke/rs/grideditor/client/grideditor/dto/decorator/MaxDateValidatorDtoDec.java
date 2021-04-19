package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import net.datenwerke.rs.grideditor.client.grideditor.dto.MaxDateValidatorDto;

import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.validator.MaxDateValidator;
import com.sencha.gxt.widget.core.client.form.validator.MaxDateValidator.MaxDateMessages;

/**
 * Dto Decorator for {@link MaxDateValidatorDto}
 *
 */
public class MaxDateValidatorDtoDec extends MaxDateValidatorDto {


	private static final long serialVersionUID = 1L;

	public MaxDateValidatorDtoDec() {
		super();
	}

	@Override
	public Validator<?> getValidator() {
		MaxDateValidator maxDateValidator = new MaxDateValidator(getMaxDate());
		maxDateValidator.setMessages(new MaxDateMessages() {
			@Override
			public String dateMaxText(String max) {
				return getErrorMsg();
			}
		});
		return maxDateValidator;
	}
}
