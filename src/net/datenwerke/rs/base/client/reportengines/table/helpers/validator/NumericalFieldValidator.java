package net.datenwerke.rs.base.client.reportengines.table.helpers.validator;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;

import net.datenwerke.gxtdto.client.i18n.I18nToolsUIService;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.locale.FilterMessages;

public class NumericalFieldValidator implements Validator<String> {

	@Inject
	protected static I18nToolsUIService i18nTools;
	
	@Override
	public List<EditorError> validate(Editor<String> editor, String value) {
		if(null == value)
			return null;
		
		value = i18nTools.translateNumberFromUserToSystem(value);
		
		try{
			if(value.contains("$") && value.contains("{") && value.contains("}"))
				return null;
			if(value.contains("*") || value.contains("?")){
				if(! value.matches("^[0-9.,*?\\-]*$")){
					List<EditorError> list = new ArrayList<EditorError>();
					list.add(new DefaultEditorError(editor, FilterMessages.INSTANCE.validationErrorNoNumberFormatInvalidCharacter(value), value));
					return list;
				}
				
				if(value.contains(",") && value.contains(".")){
					List<EditorError> list = new ArrayList<EditorError>();
					list.add(new DefaultEditorError(editor, FilterMessages.INSTANCE.validationErrorNoNumberFormatInvalidCharacter(value), value));
					return list;
				}
			} else 
				NumberFormat.getDecimalFormat().parse(value);
		} catch(NumberFormatException e){
			List<EditorError> list = new ArrayList<EditorError>();
			list.add(new DefaultEditorError(editor, FilterMessages.INSTANCE.validationErrorNoNumberFormatInvalidCharacter(value), value));
			return list;
		}
		
		
		return null;
	}

}
