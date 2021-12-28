package net.datenwerke.rs.base.client.reportengines.table.helpers.validator;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.form.Validator;
import com.sencha.gxt.widget.core.client.form.error.DefaultEditorError;

import net.datenwerke.rs.base.client.reportengines.table.columnfilter.locale.FilterMessages;

public class SqlDateValidator implements Validator<String> {

	@Override
	public List<EditorError> validate(Editor<String> editor, String value) {
		if(null == value)
			return null;
		
		try{
			if(value.contains("$") && value.contains("{") && value.contains("}"))
				return null;
			
			parse(value);
		} catch(IllegalArgumentException e){
			List<EditorError> list = new ArrayList<EditorError>();
			list.add(new DefaultEditorError(editor, FilterMessages.INSTANCE.validationErrorDate(value), value));
			return list;
		}
		
		
		return null;
	}

	/* code taken from java.sql.timestamp */
	private void parse(String s) {
		final int YEAR_LENGTH = 4;
		final int MONTH_LENGTH = 2;
		final int DAY_LENGTH = 2;
		final int MAX_MONTH = 12;
		final int MAX_DAY = 31;
		int firstDash;
		int secondDash;
		Date d = null;

		if (s == null) {
			throw new java.lang.IllegalArgumentException();
		}

		firstDash = s.indexOf('-');
		secondDash = s.indexOf('-', firstDash + 1);

		if ((firstDash > 0) && (secondDash > 0) && (secondDash < s.length() - 1)) {
			String yyyy = s.substring(0, firstDash);
			String mm = s.substring(firstDash + 1, secondDash);
			String dd = s.substring(secondDash + 1);
			if (yyyy.length() == YEAR_LENGTH &&
					(mm.length() >= 1 && mm.length() <= MONTH_LENGTH) &&
					(dd.length() >= 1 && dd.length() <= DAY_LENGTH)) {
				int year = Integer.parseInt(yyyy);
				int month = Integer.parseInt(mm);
				int day = Integer.parseInt(dd);

				if ((month >= 1 && month <= MAX_MONTH) && (day >= 1 && day <= MAX_DAY)) {
					d = new Date(year - 1900, month - 1, day);
				}
			}
		}
		if (d == null) {
			throw new java.lang.IllegalArgumentException();
		}
	}

}
