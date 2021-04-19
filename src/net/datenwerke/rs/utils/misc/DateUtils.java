package net.datenwerke.rs.utils.misc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	private DateUtils(){
		//no instances
	}
	
	 /**
     * All minutes have this many milliseconds except the last minute of the day on a day defined with
     * a leap second.
     */
    public static final long MILLISECS_PER_MINUTE = 60*1000;
    
    /**
     * Number of milliseconds per hour, except when a leap second is inserted.
     */
    public static final long MILLISECS_PER_HOUR   = 60*MILLISECS_PER_MINUTE;
    
    /**
     * Number of leap seconds per day expect on 
     * <BR/>1. days when a leap second has been inserted, e.g. 1999 JAN  1.
     * <BR/>2. Daylight-savings "spring forward" or "fall back" days.
     */
    protected static final long MILLISECS_PER_DAY = 24*MILLISECS_PER_HOUR;
	
    /****
     * Value to add to the day number returned by this calendar to find the Julian Day number.
     * This is the Julian Day number for 1/1/1970.
     * Note: Since the unix Day number is the same from local midnight to local midnight adding
     * JULIAN_DAY_OFFSET to that value results in the chronologist, historians, or calenderists
     * Julian Day number.
     * Refer to http://www.hermetic.ch/cal_stud/jdn.htm
     */
    public static final long EPOCH_UNIX_ERA_DAY = 2440588L;
	
	
    /**
     * @return Day number where day 0 is 1/1/1970, as per the Unix/Java date/time epoch.
     */
    public static long getUnixDay(Calendar date) {
        long offset = date.get(Calendar.ZONE_OFFSET) + date.get(Calendar.DST_OFFSET);
        long day = (long)Math.floor( (double)(date.getTime().getTime() + offset ) / ((double)MILLISECS_PER_DAY) );
        return day;
    }
	
	/**
     * @return LOCAL Chronologists Julian day number each day starting from midnight LOCAL TIME.
     * Refer to http://tycho.usno.navy.mil/mjd.html for more information about local C-JDN
     */
    public static long getJulianDay(Calendar date) {
        return getUnixDay(date) + EPOCH_UNIX_ERA_DAY;
    }

    /**
     * 
     * @param d1
     * @param d2
     * @return d2 - d1
     */
    public static int getDeltaDays(Date d1, Date d2){
    	Calendar cal1 = Calendar.getInstance();
    	cal1.setTime(d1);
    	
    	Calendar cal2 = Calendar.getInstance();
    	cal2.setTime(d2);
    	
    	return (int) (getJulianDay(cal2) - getJulianDay(cal1));
    }
    
    public static String formatDate(Date date, String format){
    	return new SimpleDateFormat(format).format(date);
    }
    
    public static Date trimSecondsAndMillis(Date date) {
    	if (null == date)
  		  return null;
    	
        Calendar cal = Calendar.getInstance();
        cal.clear(); /* to avoid timezone issues */
        cal.setTime( date );
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
   }
  
  public static Date trimTimeInformation(Date date) {
	  if (null == date)
		  return null;
	  
      Calendar cal = Calendar.getInstance();
      cal.clear(); /* to avoid timezone issues */
      cal.setTime( date );
      cal.set(Calendar.HOUR_OF_DAY, 0);
      cal.set(Calendar.MINUTE, 0);
      cal.set(Calendar.SECOND, 0);
      cal.set(Calendar.MILLISECOND, 0);
      return cal.getTime();
  }
  
  /* leave only the time information in the date */
  public static Date trimDateInformation(Date date) {
	  if (null == date)
		  return null;
	  
      Calendar cal = Calendar.getInstance();
      cal.clear(); /* to avoid timezone issues */
      cal.setTime( date );
      cal.set(Calendar.DAY_OF_MONTH, 1);
      cal.set(Calendar.MONTH, 0);
      cal.set(Calendar.YEAR, 1970);
      return cal.getTime();
  }
}
