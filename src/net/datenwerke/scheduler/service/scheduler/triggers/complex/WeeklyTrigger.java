package net.datenwerke.scheduler.service.scheduler.triggers.complex;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.Table;

import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.DateTriggerConfig;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.WeeklyConfig;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Days;

import org.apache.commons.lang3.time.DateUtils;

@Entity
@Table(name="SCHED_TRIG_WEEKLY")
public class WeeklyTrigger extends DateTrigger<WeeklyConfig>{

	public WeeklyTrigger(){
		this(null);
	}
	
	public WeeklyTrigger(WeeklyConfig config) {
		super(config);
	}
	
	
	public Date getMondayBefore(Date date){
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
	
	public Date nextIncludedDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		for(int n = 0; n < 7; n++){
			Days dayOfWeek = Days.fromCalendarDay(cal.get(java.util.Calendar.DAY_OF_WEEK));
			if(getConfig().getWeeklyDays().contains(dayOfWeek)){
				return cal.getTime();
			}
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		throw new IllegalArgumentException("No matching day in a whole week"); //$NON-NLS-1$
	}
	
	/**
	 * How many weeks have passed, since the last included week
	 * 
	 * @param d
	 */
	public boolean isGoodWeek(Date d){
		/* compute first real execution by hand */
		Date firstMonday = getMondayBefore(nextIncludedDay(getFirstFireTime()));
		long daysSinceFirstMonday = net.datenwerke.rs.utils.misc.DateUtils.getDeltaDays(firstMonday, d);
		int weekNum = (int) (daysSinceFirstMonday / 7);
		int everyNthWeek = getConfig().getWeeklyN();
		
		/* How many weeks have passed, since the last included week */
		int offset = weekNum % everyNthWeek;
		
		return 0 == offset;
	}
	
	@Override
	public Date getFirstIncludedDayBefore(Date date){
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, getConfig().getWeeklyN() * -14);
		cal.setTime(_computeNextFireTime(cal.getTime(), true));
		
		Date last = null;
		while(! date.before(cal.getTime())){
			last = cal.getTime();
			cal.setTime(_computeNextFireTime(cal.getTime(), true));
		}
		
		if(null != last)
			return last;
		
		throw new IllegalArgumentException("The first fire time comes before the submitted date"); //$NON-NLS-1$
	}

	@Override
	public Date _computeNextFireTime(Date afterTime, boolean isFirstRun) {
		if(isGoodWeek(afterTime)){
			Date adjustedDate = isFirstRun ? DateUtils.addSeconds(afterTime,1) : afterTime;
			Date next = getNthFireTimeOfDayAfter(nextIncludedDay(adjustedDate), 1);
			if(null == next){
				Calendar cal = new GregorianCalendar();
				cal.setTime(afterTime);
				cal = DateUtils.truncate(cal, Calendar.DATE);
				cal.add(Calendar.DAY_OF_MONTH,1);
				return _computeNextFireTime(cal.getTime(), false);
			} else if(isGoodWeek(next)){
				/* reset seconds */
				next = DateUtils.truncate(next, Calendar.MINUTE);
				
				return next; /* we have won */
			}
		} 

		Calendar cal = new GregorianCalendar();
		cal.setTime(getMondayBefore(afterTime));
		cal = DateUtils.truncate(cal, Calendar.DATE);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		return _computeNextFireTime(cal.getTime(), false);
	}
	
	@Override
	public Date computeFirstFireTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(config.getFirstExecution());
		for(int n = 0; n < 7; n++){
			Days dayOfWeek = Days.fromCalendarDay(cal.get(java.util.Calendar.DAY_OF_WEEK));
			if(getConfig().getWeeklyDays().contains(dayOfWeek))
				break;
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		/* get first time */
		switch(config.getDailyRepeatType()){
		case ONCE:
			cal.set(Calendar.HOUR_OF_DAY,config.getAtTime().getHour());
			cal.set(Calendar.MINUTE,config.getAtTime().getMinutes());
			cal.set(Calendar.SECOND, 0);
			break;
		case BOUNDED_INTERVAL:
			cal.set(Calendar.HOUR_OF_DAY,config.getTimeRangeStart().getHour());
			cal.set(Calendar.MINUTE,config.getTimeRangeStart().getMinutes());
			cal.set(Calendar.SECOND, 0);
			break;
		}
		
		return cal.getTime();
	}
	
	
	@Override
	public boolean consumes(DateTriggerConfig config) {
		return
		(	super.consumes(config) &&
				
			config instanceof WeeklyConfig &&  
				
			null != ((WeeklyConfig)config).getWeeklyN() &&
			null != ((WeeklyConfig)config).getWeeklyDays() &&
			! ((WeeklyConfig)config).getWeeklyDays().isEmpty()
		);
	}

}
