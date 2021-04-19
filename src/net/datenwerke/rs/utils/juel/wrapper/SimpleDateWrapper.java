package net.datenwerke.rs.utils.juel.wrapper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 *
 */
public class SimpleDateWrapper {

	protected final Date date;
	
	public SimpleDateWrapper(){
		date = Calendar.getInstance().getTime();
	}
	
	public SimpleDateWrapper(Date date){
		this.date = date;
	}
	
	public Date getDate(){
		return date;
	}
	
	public String format(){
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hhmm"); //$NON-NLS-1$
		return format.format(date);
	}
	
	public String format(String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	@Override
	public String toString() {
		return format();
	}
}
