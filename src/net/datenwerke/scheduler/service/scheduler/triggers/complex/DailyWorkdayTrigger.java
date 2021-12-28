package net.datenwerke.scheduler.service.scheduler.triggers.complex;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.time.DateUtils;

import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.DailyConfig;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.DateTriggerConfig;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.DailyPattern;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Days;


/**
 * Currently implemented as working day = mo-fr
 * 
 *
 */
@Entity
@Table(name="SCHED_TRIG_DAILY_WORKDAY")
public class DailyWorkdayTrigger extends DateTrigger<DailyConfig>{

	public DailyWorkdayTrigger(){
		this(null);
	}
	
	public DailyWorkdayTrigger(DailyConfig config) {
		super(config);
	}

	public boolean isGoodDay(Date day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(day);
		Days dayOfWeek = Days.fromCalendarDay(cal.get(java.util.Calendar.DAY_OF_WEEK));
		return DateTriggerHelper.daysEqual(dayOfWeek, Days.WORKINGDAY);
	}
	
	
	@Override
	public Date _computeNextFireTime(Date afterTime, boolean isFirstRun){
		if(isGoodDay(afterTime)){
			Date adjustedDate = isFirstRun ? DateUtils.addSeconds(afterTime,1) : afterTime;
			Date next = getNthFireTimeOfDayAfter(adjustedDate, 1);
			if(null != next){
				/* reset seconds */
				next = DateUtils.truncate(next, Calendar.MINUTE);
				return next; /* we have won */
			}
		} 

		Calendar cal = new GregorianCalendar();
		cal.setTime(afterTime);
		cal = DateUtils.truncate(cal, Calendar.DATE);
		cal.add(Calendar.DAY_OF_MONTH,1);
		return _computeNextFireTime(cal.getTime(), false);
	}

	@Override
	public Date computeFirstFireTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(nextIncludedDay(config.getFirstExecution()));
	
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


	
	public Date nextIncludedDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		for(int n = 0; n < 7; n++){
			Days dayOfWeek = Days.fromCalendarDay(cal.get(java.util.Calendar.DAY_OF_WEEK));
			if(DateTriggerHelper.daysEqual(dayOfWeek, Days.WORKINGDAY)){
				return cal.getTime();
			}
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}

		throw new IllegalArgumentException("No matching day in a whole week"); //$NON-NLS-1$
	}



	protected Date getFirstIncludedDayBefore(Date date){
		/* subtract 2n days from last execution then there should definately be an execution between last and then */
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -10);
		cal.setTime(_computeNextFireTime(cal.getTime(), true));
		
		Date last = cal.getTime();
		while(! date.before(cal.getTime())){
			last = cal.getTime();
			cal.setTime(_computeNextFireTime(cal.getTime(), true));
		}
		
		if(null != last)
			return last;
		
		throw new IllegalArgumentException("The first fire time comes before the submitted date"); //$NON-NLS-1$
	}
	
	@Override
	public boolean consumes(DateTriggerConfig config) {
		return
		(	
			super.consumes(config) &&
			
			config instanceof DailyConfig && 
			
			DailyPattern.DAILY_WORKDAY.equals(((DailyConfig)config).getPattern())
		);
	}

}
