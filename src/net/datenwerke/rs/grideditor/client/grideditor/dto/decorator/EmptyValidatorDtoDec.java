package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.rs.grideditor.client.grideditor.dto.EmptyValidatorDto;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;

/**
 * Dto Decorator for {@link EmptyValidatorDto}
 *
 */
public class EmptyValidatorDtoDec extends EmptyValidatorDto {


	private static final long serialVersionUID = 1L;

	public EmptyValidatorDtoDec() {
		super();
	}

	@Override
	public Validator<?> getValidator() {
		return new AbstractValidator<Object>() {

			@Override
			public List<EditorError> validate(Editor<Object> editor,
					Object value) {
				if (value == null || "".equals(String.valueOf(value))) {
					List<EditorError> errors = new ArrayList<EditorError>();
					errors.add(new DefaultEditorError(editor, getErrorMsg(), ""));
					return errors;
				}
				return null;
			}
		};
	}

}
