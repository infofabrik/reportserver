package net.datenwerke.rs.grideditor.client.grideditor.dto.decorator;

import java.util.List;

import net.datenwerke.rs.grideditor.client.grideditor.dto.MinLengthValidatorDto;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;
import com.sencha.gxt.widget.core.client.form.validator.MinLengthValidator;
import com.sencha.gxt.widget.core.client.form.validator.MinLengthValidator.MinLengthMessages;

/**
 * Dto Decorator for {@link MinLengthValidatorDto}
 *
 */
public class MinLengthValidatorDtoDec extends MinLengthValidatorDto {


	private static final long serialVersionUID = 1L;

	public MinLengthValidatorDtoDec() {
		super();
	}

	@Override
	public Validator<?> getValidator() {
		MinLengthValidator validator = new MinLengthValidator(getLength()){
			@Override
			public List<EditorError> validate(Editor<String> field, String value) {
				if(null == value)
					return createError(new DefaultEditorError(field, getMessages().minLengthText(minLength), value));
				return super.validate(field, value);
			}
		};
		validator.setMessages(new MinLengthMessages() {
			@Override
			public String minLengthText(int length) {
				return getErrorMsg();
			}
		});
		return validator;
	}
}
