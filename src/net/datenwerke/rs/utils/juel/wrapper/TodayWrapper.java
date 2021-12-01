package net.datenwerke.rs.utils.juel.wrapper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class TodayWrapper extends SimpleDateWrapper {

	public static final String TODAY = "today";
	
	private String defaultFormat = "yyyyMMdd";

	public TodayWrapper(){
		super();
	}
	
	public TodayWrapper(Date date, String defaultFormat) {
		super(date);
		this.defaultFormat = defaultFormat;
	}
	
	public TodayWrapper(String defaultFormat) {
		this.defaultFormat = defaultFormat;
	}

	public TodayWrapper addSeconds(int seconds){
		return new TodayWrapper(DateUtils.addSeconds(getDate(), seconds), defaultFormat);
	}
	
	public TodayWrapper addMinutes(int minutes){
		return new TodayWrapper(DateUtils.addMinutes(getDate(), minutes), defaultFormat);
	}

	public TodayWrapper addHours(int hours){
		return new TodayWrapper(DateUtils.addHours(getDate(), hours), defaultFormat);
	}

	
	public TodayWrapper addWeeks(int weeks){
		return new TodayWrapper(DateUtils.addWeeks(getDate(), weeks), defaultFormat);
	}
	
	public TodayWrapper addDays(int days){
		return new TodayWrapper(DateUtils.addDays(getDate(), days), defaultFormat);
	}
	
	public TodayWrapper addMonths(int months){
		return new TodayWrapper(DateUtils.addMonths(getDate(), months), defaultFormat);
	}
	
	public TodayWrapper addYears(int years){
		return new TodayWrapper(DateUtils.addYears(getDate(), years), defaultFormat);
	}
	
	public TodayWrapper firstDay(){
		return new TodayWrapper(DateUtils.setDays(getDate(), 1), defaultFormat).clearTime();
	}
	
	public TodayWrapper lastDay(){
		return firstDay().addMonths(1).addSeconds(-1);
	}
	
	/**
	 * Sets the date to match the next weekday. If today matches the specified weekday, then nothing will change.
	 */
	public TodayWrapper setNextWeekday(int day){
		day = ((day - 1) % 7) + 1; // ensure day between 1 and 7 including
		
		Calendar c = Calendar.getInstance();
		c.setTime(getDate());
		int nextDay = c.get(Calendar.DAY_OF_WEEK);
		
		while(nextDay != day){
			c.setTime(DateUtils.addDays(c.getTime(), 1));
			nextDay = c.get(Calendar.DAY_OF_WEEK);
		}
		
		return new TodayWrapper(c.getTime(), defaultFormat);
	}
		
	public TodayWrapper setPrevWeekday(int day){
		day = ((day - 1) % 7) + 1; // ensure day between 1 and 7 including
		
		Calendar c = Calendar.getInstance();
		c.setTime(getDate());
		int prevDay = c.get(Calendar.DAY_OF_WEEK);
		
		while(prevDay != day){
			c.setTime(DateUtils.addDays(c.getTime(), -1));
			prevDay = c.get(Calendar.DAY_OF_WEEK);
		}
		
		return new TodayWrapper(c.getTime(), defaultFormat);
	}
	
	public TodayWrapper setDay(int day){
		return new TodayWrapper(DateUtils.setDays(getDate(), day), defaultFormat);
	}
	
	public TodayWrapper setMonth(int month){
		return new TodayWrapper(DateUtils.setMonths(getDate(), month - 1), defaultFormat);
	}
	
	public TodayWrapper setYear(int years){
		return new TodayWrapper(DateUtils.setYears(getDate(), years), defaultFormat);
	}
	
	public TodayWrapper setDate(int day, int month, int years){
		Date d = getDate();
		d = DateUtils.setDays(d, day);
		d = DateUtils.setMonths(d, month - 1);
		d = DateUtils.setYears(d, years);
		return new TodayWrapper(d, defaultFormat);
	}
	
	public TodayWrapper setSeconds(int seconds){
		return new TodayWrapper(DateUtils.setSeconds(getDate(), seconds), defaultFormat);
	}

	public TodayWrapper setMinutes(int minutes){
		return new TodayWrapper(DateUtils.setMinutes(getDate(), minutes), defaultFormat);
	}
	
	public TodayWrapper setHours(int hours){
		return new TodayWrapper(DateUtils.setHours(getDate(), hours), defaultFormat);
	}

	public TodayWrapper clearTime(){
		return new TodayWrapper(DateUtils.truncate(date, java.util.Calendar.DATE), defaultFormat);
	}
	
	@Override
	public String format() {
		return new SimpleDateFormat(defaultFormat).format(getDate());
	}
	
}
