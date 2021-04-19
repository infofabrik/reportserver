package net.datenwerke.scheduler.service.scheduler.triggers.complex;

import java.util.Calendar;
import java.util.Date;

import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Days;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Nth;

public class DateTriggerHelper {

	/**
	 * Returnes whether to days "mean" the same. 
	 * 
	 * Monday  = Monday
	 * Tuesday = Tuesday
	 * ...     = ...
	 * Weekday = Monday, Tuesday...
	 * 
	 * @param day1
	 * @param day2
	 */
	public static boolean daysEqual(Days day1, Days day2){
		if(day1.equals(day2))
			return true;

		if(day1.equals(Days.DAY) || day2.equals(Days.DAY))
			return true;
		
		if(day1.equals(Days.WORKINGDAY) && day2.ordinal() <= Days.FRIDAY.ordinal())
			return true;
		
		if(day2.equals(Days.WORKINGDAY) && day1.ordinal() <= Days.FRIDAY.ordinal())
			return true;
		
		if(day1.equals(Days.WEEKENDDAY) && (day2.equals(Days.SATURDAY) || day2.equals(Days.SUNDAY)))
			return true;
		
		if(day2.equals(Days.WEEKENDDAY) && (day1.equals(Days.SATURDAY) || day1.equals(Days.SUNDAY)))
			return true;
		
		return false;
	}

	
	public static Date getDayInMonth(Date date, Nth nth, Days day){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int initialMonth = cal.get(Calendar.MONTH);
		
		int n = 0;
		Date lastMatch = null;
		
		while(initialMonth == cal.get(Calendar.MONTH)){
			if(daysEqual(Days.fromCalendarDay(cal.get(Calendar.DAY_OF_WEEK)), day)){
				lastMatch = cal.getTime();
				
				if(nth.ordinal() == n && ! nth.equals(Nth.LAST)){
					return lastMatch;
				}
				
				n++;
			}
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		if(nth == Nth.LAST){
			return lastMatch;
		}else{
			return null;
		}
	}
	
	
	/**
	 * get the date of the last monday before the given date
	 * 
	 * @param date
	 */
	protected static Date getMondayBefore(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		for(int n = 0; n < 7; n++){
			Days dayOfWeek = Days.fromCalendarDay(cal.get(java.util.Calendar.DAY_OF_WEEK));
			if(dayOfWeek.equals(Days.MONDAY)){
				return cal.getTime();
			}
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		
		throw new IllegalArgumentException("This week has no monday???"); //$NON-NLS-1$
	}

}
