package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.rs.grideditor.client.grideditor.dto.CustomValidatorDto;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;

/**
 * Dto Decorator for {@link CustomValidatorDto}
 *
 */
public class CustomValidatorDtoDec extends CustomValidatorDto {


	private static final long serialVersionUID = 1L;

	public CustomValidatorDtoDec() {
		super();
	}

	@Override
	public Validator<?> getValidator() {
		return new AbstractValidator<Object>() {

			@Override
			public List<EditorError> validate(Editor<Object> editor,
					Object value) {
				String val = getClientValidator();
				if(null == val || "".equals(val.trim()))
					return null;
				
				if(! doValidate(val, value) ){
					List<EditorError> errors = new ArrayList<EditorError>();
					errors.add(new DefaultEditorError(editor, getErrorMsg(), ""));
					return errors;
				}

				return null;
			}
		};
	}

	protected native boolean doValidate(String validator, Object value) /*-{
		var fun = Function("value", validator);
		return fun(value);
	}-*/;
}
