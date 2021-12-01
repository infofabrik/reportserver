package net.datenwerke.rs.base.client.parameters.datetime.dto.decorator;

import java.util.Date;

import net.datenwerke.gf.client.uiutils.date.DateFormulaContainer;
import net.datenwerke.rs.base.client.parameters.datetime.dto.DateTimeParameterInstanceDto;
import net.datenwerke.rs.base.client.parameters.datetime.dto.pa.DateTimeParameterInstanceDtoPA;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.sencha.gxt.core.client.ValueProvider;

/**
 * Dto Decorator for {@link DateTimeParameterInstanceDto}
 *
 */
public class DateTimeParameterInstanceDtoDec extends DateTimeParameterInstanceDto {

	public static class DateFormulaValueProvider implements ValueProvider<DateTimeParameterInstanceDto,DateFormulaContainer>{

		@Override
		public DateFormulaContainer getValue(DateTimeParameterInstanceDto object) {
			return null == object ? null : ((DateTimeParameterInstanceDtoDec)object).getDateFormula();
		}

		@Override
		public void setValue(DateTimeParameterInstanceDto object,
				DateFormulaContainer value) {
			if(null == object)
				return;
			
			((DateTimeParameterInstanceDtoDec)object).setDateFormula(value);
		}

		@Override
		public String getPath() {
			return "value";
		}
	}

	private static final long serialVersionUID = 1L;

	public DateTimeParameterInstanceDtoDec() {
		super();
	}

	public DateFormulaContainer getDateFormula(){
		return new DateFormulaContainer(getValue(), getFormula());
	}
	
	public void setDateFormula(DateFormulaContainer formulaDate) {
		
		/* date formula changed events should only be fired after both fields were updated */
		boolean wasSilent = isSilenceEvents();
		silenceEvents(true);
		
		String oldFormulaValue = getFormula();
		String oldStrValue = getStrValue();
		
		if(null == formulaDate){
			setValue(null);
			setFormula(null);
		} else {
			if(null != formulaDate.getFormula()){
				setValue(null);
				setFormula(formulaDate.getFormula());
			} else {
				setFormula(null);
				setValue(formulaDate.getDate());
			}
		}
		
		silenceEvents(wasSilent);
		this.fireObjectChangedEvent(DateTimeParameterInstanceDtoPA.INSTANCE.strValue(), oldStrValue);
		this.fireObjectChangedEvent(DateTimeParameterInstanceDtoPA.INSTANCE.formula(), oldFormulaValue);
	}
	
	@Override
	public void setValue(Date value) {
		super.setValue(value);
		if(GWT.isClient()){
			if(null != value) {
				setStrValue(DateTimeFormat.getFormat(PredefinedFormat.ISO_8601).format(value));
			} else {
				setStrValue(null);
			}
		}
	}
	
	public static DateFormulaValueProvider getDateFormulaValueProvider(){
		return new DateFormulaValueProvider();
	}
	
}
