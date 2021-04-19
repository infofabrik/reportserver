package net.datenwerke.scheduler.service.scheduler.triggers.complex;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.Table;

import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.DailyConfig;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.DateTriggerConfig;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.DailyPattern;

import org.apache.commons.lang.time.DateUtils;

@Entity
@Table(name="SCHED_TRIG_DAILY_NTHDAY")
public class DailyNthdayTrigger extends DateTrigger<DailyConfig>{

	public DailyNthdayTrigger(){
		this(null);
	}
	
	public DailyNthdayTrigger(DailyConfig config) {
		super(config);
	}

	@Override
	public Date computeFirstFireTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(config.getFirstExecution());
	
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
	
	public boolean isGoodDay(Date day) {
		int delta = net.datenwerke.rs.utils.misc.DateUtils.getDeltaDays(getFirstFireTime(), day);
		return 0 == delta % getConfig().getDailyN();
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

		int delta = net.datenwerke.rs.utils.misc.DateUtils.getDeltaDays(getFirstFireTime(), afterTime);
		int remaining = getConfig().getDailyN() - delta % getConfig().getDailyN();
		
		Calendar cal = new GregorianCalendar();
		cal.setTime(afterTime);
		cal = DateUtils.truncate(cal, Calendar.DATE);
		cal.add(Calendar.DAY_OF_MONTH,remaining);
		return _computeNextFireTime(cal.getTime(), false);
	}

	@Override
	protected Date getFirstIncludedDayBefore(Date date){
		/* subtract 2n days from last execution then there should definately be an execution between last and then */
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, getConfig().getDailyN() * -2);
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
		(	super.consumes(config) &&
				
			config instanceof DailyConfig && 
				
			DailyPattern.DAILY_EVERY_Nth_DAY.equals(((DailyConfig)config).getPattern()) && 
			
			null != ((DailyConfig)config).getDailyN()
		);
	}
}
