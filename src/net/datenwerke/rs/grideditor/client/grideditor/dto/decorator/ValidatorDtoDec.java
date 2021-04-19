package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import net.datenwerke.rs.grideditor.client.grideditor.dto.ValidatorDto;

import com.sencha.gxt.widget.core.client.form.Validator;

/**
 * Dto Decorator for {@link ValidatorDto}
 *
 */
abstract public class ValidatorDtoDec extends ValidatorDto {


	private static final long serialVersionUID = 1L;

	public ValidatorDtoDec() {
		super();
	}

	public abstract Validator<?> getValidator();
}
