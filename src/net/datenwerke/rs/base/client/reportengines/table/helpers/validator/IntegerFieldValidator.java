package net.datenwerke.rs.base.client.reportengines.table.helpers.validator;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.rs.base.client.reportengines.table.columnfilter.locale.FilterMessages;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.i18n.client.NumberFormat;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;

public class IntegerFieldValidator implements Validator<String> {

	@Override
	public List<EditorError> validate(Editor<String> editor, String value) {
		if(null == value)
			return null;
		
		try{
			if(value.contains("$") && value.contains("{") && value.contains("}"))
				return null;
			if(! value.contains("*") && ! value.contains("?") )
				NumberFormat.getDecimalFormat().parse(value);
			if(! value.matches("^[0-9*?\\-]*$")){
				List<EditorError> list = new ArrayList<EditorError>();
				list.add(new DefaultEditorError(editor, FilterMessages.INSTANCE.validationErrorNoIntegerFormatInvalidCharacter(value), value));
				return list;
			}
		} catch(NumberFormatException e){
			List<EditorError> list = new ArrayList<EditorError>();
			list.add(new DefaultEditorError(editor, FilterMessages.INSTANCE.validationErrorNoNumberFormatInvalidCharacter(value), value));
			return list;
		}
		
		return null;
	}
}
