package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import java.math.BigDecimal;

import net.datenwerke.rs.grideditor.client.grideditor.dto.MinBigDecimalValidatorDto;

import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.validator.MaxNumberValidator;
import com.sencha.gxt.widget.core.client.form.validator.MaxNumberValidator.MaxNumberMessages;

/**
 * Dto Decorator for {@link MinBigDecimalValidatorDto}
 *
 */
public class MinBigDecimalValidatorDtoDec extends MinBigDecimalValidatorDto {


	private static final long serialVersionUID = 1L;

	public MinBigDecimalValidatorDtoDec() {
		super();
	}

	@Override
	public Validator<?> getValidator() {
		MaxNumberValidator<BigDecimal> validator = new MaxNumberValidator<BigDecimal>(getNumber());
		validator.setMessages(new MaxNumberMessages() {
			@Override
			public String numberMaxText(double max) {
				return getErrorMsg();
			}
			
			@Override
			public String numberMaxText(String max) {
				return getErrorMsg();
			}
		});
		return validator;
	}
}
