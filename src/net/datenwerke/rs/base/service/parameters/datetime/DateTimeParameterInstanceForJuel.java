package net.datenwerke.rs.base.service.parameters.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.datenwerke.rs.core.service.parameters.entities.ParameterInstanceForJuel;

public class DateTimeParameterInstanceForJuel extends ParameterInstanceForJuel {
	
	private Date value;

	public void setValue(Date value) {
		this.value = value;
	}

	public Date getValue() {
		return value;
	}

	public String format(){
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy"); //$NON-NLS-1$
		return format.format(value);
	}
	
	public String format(String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try{
			return format.format(value);
		} catch(Exception e){
			throw new RuntimeException("Could not format date " + value + " with pattern: " + pattern, e);
		}
		
	}

}
