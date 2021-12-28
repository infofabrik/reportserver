package net.datenwerke.scheduler.service.scheduler.triggers.complex;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.time.DateUtils;

import net.datenwerke.scheduler.service.scheduler.entities.AbstractTrigger;
import net.datenwerke.scheduler.service.scheduler.locale.SchedulerServerMessages;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.DateTriggerConfig;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.Time;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.DailyRepeatType;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.EndTypes;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.TimeUnits;

@Entity
@Table(name="SCHED_TRIG_DATE")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class DateTrigger<C extends DateTriggerConfig> extends AbstractTrigger {
	
	@OneToOne(cascade=CascadeType.ALL)
	protected DateTriggerConfig config;

	public DateTrigger(){
		this(null);
	}
	
	public DateTrigger(C config){
		if(null != config && !consumes(config))
			throw new IllegalArgumentException(SchedulerServerMessages.INSTANCE.triggerConfigException());
		this.config = config;
	}
	
	public void setConfig(C config) {
		if(null != config && !consumes(config))
			throw new IllegalArgumentException(SchedulerServerMessages.INSTANCE.triggerConfigException());
		this.config = config;
	}
	
	public C getConfig(){
		return (C) config;
	}
	
	/**
	 * Returns the given date with the time component set to the 
	 * time of the first execution of the trigger at this day. 
	 * 
	 * Does not check if the trigger does fire at this day at all.
	 * 
	 * @param day
	 */
	public Date getFirstFireTimeOfDay(Date day){
		Date cleanedDay = DateUtils.truncate(day, java.util.Calendar.DATE);
		
		if(DailyRepeatType.ONCE.equals(config.getDailyRepeatType())){
			long timeInMinutes = DateUtils.getFragmentInMinutes(config.getAtTime().getDate(), java.util.Calendar.DATE);
			return setTimeOfDay(cleanedDay,(int)timeInMinutes).getTime();
		} else if(DailyRepeatType.BOUNDED_INTERVAL.equals(config.getDailyRepeatType())){
			long timeInMinutes = DateUtils.getFragmentInMinutes(config.getTimeRangeStart().getDate(), java.util.Calendar.DATE);
			return setTimeOfDay(cleanedDay,(int)timeInMinutes).getTime();
		} else
			throw new IllegalStateException("No valid DailyRepeatTime gesetzt."); //$NON-NLS-1$
	}
	
	/**
	 * Sets the time component of the supplied date to the time, the
	 * trigger fires the last time at this date. 
	 * 
	 * Does not check, if the trigger fires at this dayat all
	 * 
	 * @param day
	 */
	public Date getLastFireTimeOfDay(Date day){
		return getNthFireTimeOfDay(day, getFiringsPerDay());
	}

	/**
	 * Returns the supplied date, with its time component set to the
	 * time of the nth execution of the trigger after the time given 
	 * 
	 * Does not check if the trigger fires at all
	 * 
	 * @param day
	 * @param n
	 * @throws IllegalArgumentException
	 */
	public Date getNthFireTimeOfDayAfter(Date day, int n){
		switch(config.getDailyRepeatType()){
		case ONCE:{ 
			Time time = config.getAtTime();

			long dayElapsedSeconds = DateUtils.getFragmentInSeconds(day, java.util.Calendar.DATE);
			
			/* test if we have already passed the firing time */
			long fireTimeSeconds = DateUtils.getFragmentInSeconds(time.getDate(), java.util.Calendar.DATE);
			if(dayElapsedSeconds > fireTimeSeconds)
				return null;
			
			/* set proper time */
			Calendar cal = setTimeOfDay(day, time);
			
			return cal.getTime();
		}
		case BOUNDED_INTERVAL:
			Calendar cal = Calendar.getInstance();
			cal.setTime(config.getTimeRangeStart().getDate());
			cal.set(Calendar.YEAR, day.getYear() + 1900);
			cal.set(Calendar.MONTH, day.getMonth());
			cal.set(Calendar.DAY_OF_MONTH, day.getDate());
			int count = 0;
			if(day.before(cal.getTime())){
				count = 1;
			}
			
			while(count < n){

				cal.add(config.getTimeRangeUnit().getCalendarUnit(), config.getTimeRangeInterval());
				Date calTime = cal.getTime();
				
				if(day.before(calTime)){
					count++;
				}
				
				// abort if time range window was left
				Calendar end = Calendar.getInstance();
				end.setTime(config.getTimeRangeEnd().getDate());
				end.set(Calendar.YEAR, day.getYear() + 1900);
				end.set(Calendar.MONTH, day.getMonth());
				end.set(Calendar.DAY_OF_MONTH, day.getDate());
				if(end.getTime().before(cal.getTime()))
					return null;
			}
			
			return cal.getTime();
			
		}

		return null;
	}
	
	/**
	 * Returns a cleaned day object with the specified time
	 * 
	 * <p>This method does not change the time according to summer or winter time. So
	 * if you add 600 minutes and the time should have been changed overnight the resulting
	 * object will still be set to 10 am.
	 * </p>
	 * 
	 * @param day
	 * @param minutes
	 */
	private Calendar setTimeOfDay(Date day, int minutes) {
		if(minutes < 0 || minutes > 1440)
			throw new IllegalArgumentException("Please make sure not to span days: Minutes have to be less than 1440 and greate than 0. Got " + minutes); //$NON-NLS-1$
		
		int hours = minutes / 60;
		int remainingMinutes = minutes % 60;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(day);
		cal.set(Calendar.MINUTE, remainingMinutes);
		cal.set(Calendar.HOUR_OF_DAY, hours);
		
		return cal;
	}

	/**
	 * Creates a calendar object with the specified time.
	 * @param day
	 * @param t
	 */
	private Calendar setTimeOfDay(Date day, Time t) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(day);
		cal.set(Calendar.MINUTE, t.getMinutes());
		cal.set(Calendar.HOUR_OF_DAY, t.getHour());
		
		return cal;
	}

	/**
	 * returns the given date with the time part set to
	 * the time of the nth execution.
	 * 
	 * @param day
	 * @param n
	 */
	public Date getNthFireTimeOfDay(Date day, int n){
		return getNthFireTimeOfDayAfter(DateUtils.truncate(day, java.util.Calendar.DATE), n);
	}
	
	/**
	 * returns how many times the trigger fires in one day
	 * 
	 */
	public int getFiringsPerDay(){
		if(config.getDailyRepeatType().equals(DailyRepeatType.ONCE))
			return 1;
		
		long start = DateUtils.getFragmentInMinutes(config.getTimeRangeStart().getDate(), java.util.Calendar.DATE);
		long end   = DateUtils.getFragmentInMinutes(config.getTimeRangeEnd().getDate(), java.util.Calendar.DATE);
	
		int timeRangeSize = (int) (end - start);

		int intervalLengthMinutes;
		if(config.getTimeRangeUnit().equals(TimeUnits.HOURS)){
			intervalLengthMinutes = 60 * config.getTimeRangeInterval();
		}else{
			intervalLengthMinutes = config.getTimeRangeInterval();
		}
		
		return (timeRangeSize / intervalLengthMinutes) + 1;
	}
	
	/* override me */
	public boolean consumes(DateTriggerConfig config){
		return null != config.getFirstExecution();
	}
	
	public Date getFinalFireTime() {
		return getFinalFireTime(null);
	}
	
	public Date getFinalFireTime(Date lastFireTime) {
		switch(config.getEndType()){
		case INFINITE:
			return null;

		case COUNT:
			Calendar cal = new GregorianCalendar();
			int beginCnt = 1;
			if(null != lastFireTime){
				beginCnt = getNrOfSuccessfulExecutions();
				cal.setTime(lastFireTime);
			} else
				cal.setTime(getFirstFireTime());
			
			for(; beginCnt < config.getNumberOfExecutions(); beginCnt++)
				cal.setTime(_computeNextFireTime(cal.getTime(), true));
						
			return cal.getTime();
		case DATE:
			cal = new GregorianCalendar();
			cal.setTime(config.getLastExecution());
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			return getLastFireTimeOfDay(getFirstIncludedDayBefore(cal.getTime()));
		}
		return null;
	}

	@Override
	public Date computeNextFireTime(Date afterTime) {
		Date next = _computeNextFireTime(afterTime, true);
		Date finalTime = getFinalFireTime(afterTime);
		if(null != next && (null == finalTime || ! finalTime.before(next)))
			return next;

		return null;
	}
	
	
	/**
	 * as the method name suggests..
	 */
	protected abstract Date getFirstIncludedDayBefore(Date lastExecution);
	
	/**
	 * Computes the next fire time not caring about a final fire time.
	 */
	protected abstract Date _computeNextFireTime(Date time, boolean isFirstRun);
	
	@Override
	public List<Date> getNextScheduleTimes(int times) {
		if(EndTypes.COUNT == config.getEndType())
			times = Math.min(config.getNumberOfExecutions(), times);
		return super.getNextScheduleTimes(times);
	}
}
