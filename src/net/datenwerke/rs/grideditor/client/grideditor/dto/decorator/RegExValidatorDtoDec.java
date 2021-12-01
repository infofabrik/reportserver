package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.grideditor.client.grideditor.dto.RegExValidatorDto;

import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.validator.RegExValidator;

/**
 * Dto Decorator for {@link RegExValidatorDto}
 *
 */
public class RegExValidatorDtoDec extends RegExValidatorDto {


	private static final long serialVersionUID = 1L;

	public RegExValidatorDtoDec() {
		super();
	}

	@Override
	public Validator<String> getValidator() {
		String msg = getErrorMsg();
		return new RegExValidator(getRegex(), null != msg ? msg : BaseMessages.INSTANCE.error());
	}
}
