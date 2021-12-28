package net.datenwerke.gxtdto.client.forms.simpleform.providers.configs;

import java.util.List;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.regexp.shared.RegExp;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.validator.RegExValidator;

import net.datenwerke.gxtdto.client.forms.locale.FormsMessages;

public class SFFCStringValidatorRegex implements SFFCStringValidator {
	
	private String regex;
	private String msg;
	private boolean allowBlank;
	
	
	public SFFCStringValidatorRegex(String regex) {
		this(regex, FormsMessages.INSTANCE.regexDefaultErrorMessage(regex));
	}
	
	public SFFCStringValidatorRegex(String regex, String msg) {
		this.regex = regex;
		this.msg = msg;
		RegExp.compile(regex);
	}

	public Validator<String> getValidator() {
		return new Validator<String>() {
			
			@Override
			public List<EditorError> validate(Editor<String> editor, String value) {
				if(allowBlank && (null == value || value.isEmpty()))
					return null;
			
				return new RegExValidator(regex,msg).validate(editor, value);
			}
		};
	}

	@Override
	public void setAllowBlank(boolean allowBlank) {
		this.allowBlank = allowBlank;
	}

}
