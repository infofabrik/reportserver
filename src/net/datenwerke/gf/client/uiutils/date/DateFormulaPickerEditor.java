package net.datenwerke.gf.client.uiutils.date;

import java.text.ParseException;
import java.util.Date;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.sencha.gxt.widget.core.client.form.PropertyEditor;

public class DateFormulaPickerEditor extends PropertyEditor<DateFormulaContainer>{

	protected DateTimeFormat format = DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT);
	protected boolean parseStrict = true;
	
	public DateFormulaPickerEditor(){
	}
	
	public DateFormulaPickerEditor(DateTimeFormat format) {
		if(null != format)
			this.format = format;
	}

	@Override
	public String render(DateFormulaContainer object) {
		if(null == object)
			return null;
		if(null != object.getFormula())
			return object.getFormula();
		if(null != object.getDate())
			return format.format(object.getDate());
		return null;
	}

	@Override
	public DateFormulaContainer parse(CharSequence text) throws ParseException {
		if(null == text)
			return null;
		String trimmed = text.toString().trim();
		if(trimmed.startsWith("${"))
			return new DateFormulaContainer(null, trimmed);
		
		try{
			Date date = format.parseStrict(trimmed);
			return new DateFormulaContainer(date, null);
		} catch (Exception ex) {
		      throw new ParseException(ex.getMessage(), 0);
	    }
	}

	public String format(Date value) {
		return format.format(value);
	}
	
}