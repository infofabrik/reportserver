package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import java.math.BigDecimal;

import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.validator.MaxNumberValidator;
import com.sencha.gxt.widget.core.client.form.validator.MaxNumberValidator.MaxNumberMessages;

import net.datenwerke.rs.grideditor.client.grideditor.dto.MaxBigDecimalValidatorDto;

/**
 * Dto Decorator for {@link MaxBigDecimalValidatorDto}
 *
 */
public class MaxBigDecimalValidatorDtoDec extends MaxBigDecimalValidatorDto {


	private static final long serialVersionUID = 1L;

	public MaxBigDecimalValidatorDtoDec() {
		super();
	}

	@Override
	public Validator<?> getValidator() {
		MaxNumberValidator<BigDecimal> validator = new MaxNumberValidator<BigDecimal>(getNumber());
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
