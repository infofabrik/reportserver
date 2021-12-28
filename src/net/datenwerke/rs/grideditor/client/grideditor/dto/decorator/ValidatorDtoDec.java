package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import com.sencha.gxt.widget.core.client.form.Validator;

import net.datenwerke.rs.grideditor.client.grideditor.dto.ValidatorDto;

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
