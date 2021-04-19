package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import net.datenwerke.rs.grideditor.client.grideditor.dto.MinDateValidatorDto;

import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.validator.MinDateValidator;
import com.sencha.gxt.widget.core.client.form.validator.MinDateValidator.MinDateMessages;

/**
 * Dto Decorator for {@link MinDateValidatorDto}
 *
 */
public class MinDateValidatorDtoDec extends MinDateValidatorDto {


	private static final long serialVersionUID = 1L;

	public MinDateValidatorDtoDec() {
		super();
	}

	@Override
	public Validator<?> getValidator() {
		MinDateValidator minDateValidator = new MinDateValidator(getMinDate());
		minDateValidator.setMessages(new MinDateMessages() {
			@Override
			public String dateMinText(String max) {
				return getErrorMsg();
			}
		});
		return minDateValidator;
	}

}
