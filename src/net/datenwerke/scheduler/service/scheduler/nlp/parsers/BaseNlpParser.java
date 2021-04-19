package net.datenwerke.scheduler.service.scheduler.nlp.parsers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import net.datenwerke.scheduler.service.scheduler.entities.AbstractTrigger;
import net.datenwerke.scheduler.service.scheduler.nlp.hooks.NlpTriggerParserHook;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.DailyNthdayTrigger;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.DailyWorkdayTrigger;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.DateTrigger;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.MonthlyNthDayMthMonthTrigger;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.WeeklyTrigger;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.DailyConfig;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.DateTriggerConfig;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.MonthlyNthDayConfig;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.Time;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.WeeklyConfig;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.DailyRepeatType;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Days;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.EndTypes;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.TimeUnits;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrMatcher;
import org.apache.commons.lang.text.StrTokenizer;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class BaseNlpParser implements NlpTriggerParserHook {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	class ExpressionAndSub<A> {
		private A expression;
		private StrTokenizer subTokenizer;
		public ExpressionAndSub(A expression, StrTokenizer subTokenizer) {
			this.expression = expression;
			this.subTokenizer = subTokenizer;
		}
		public A getExpression() {
			return expression;
		}
		public StrTokenizer getSubTokenizer() {
			return subTokenizer;
		}
	}
	
	@Inject private Provider<DailyNthdayTrigger> dailyNthDayTriggerProvider;
	@Inject private Provider<DailyWorkdayTrigger> dailyWorkDayTriggerProvider;
	@Inject private Provider<WeeklyTrigger> weeklyTriggerProvider;
	@Inject private Provider<MonthlyNthDayMthMonthTrigger> monthlyTriggerProvider;
	
	@Override
	public AbstractTrigger parseExpression(String expression) {
		try{
			if(null == expression)
				return null;
			
			expression = expression.toLowerCase();
			
			StrTokenizer tokenizer = new StrTokenizer(expression);
			tokenizer.setDelimiterMatcher(StrMatcher.trimMatcher());
			
			if(tokenizer.getTokenList().size() < 3)
				return null;
			
			String first = tokenizer.getTokenArray()[0];
			
			if("today".equals(first))
				return parseTodayAtTimeExpression(tokenizer);
			else if("at".equals(first))
				return parseAtTrigger(tokenizer);
			else if("every".equals(first))
				return parseEveryExpression(tokenizer);
			
		} catch (Exception e) {
			logger.info( "NLP parser error", e);			
		}

		return null;
	}


	private AbstractTrigger parseEveryExpression(StrTokenizer tokenizer) {
		String second = tokenizer.getTokenArray()[1];
		
		if("hour".equals(second))
			return parseHourlyTrigger(tokenizer);
		if("day".equals(second))
			return parseDaylyExpression(tokenizer, dailyNthDayTriggerProvider.get());
		if("workday".equals(second))
			return parseDaylyExpression(tokenizer, dailyWorkDayTriggerProvider.get());
		if("week".equals(second))
			return parseWeeklyExpression(tokenizer);
		if("month".equals(second))
			return parseMonthlyExpression(tokenizer);
		
		return null;
	}
	
	private AbstractTrigger parseMonthlyExpression(StrTokenizer tokenizer) {
		tokenizer = getSubTokenizer(tokenizer, 3);
		
		ExpressionAndSub<Integer> dayExpr = getDayInMonth(tokenizer);		
		if(null != dayExpr){
			MonthlyNthDayConfig config = new MonthlyNthDayConfig();
			config.setDayInMonth(dayExpr.getExpression());
			
			tokenizer = appendTimeOfDay(config, dayExpr.subTokenizer);
			tokenizer = appendStarting(config, tokenizer);
			tokenizer = appendEnd(config, tokenizer);
			
			MonthlyNthDayMthMonthTrigger weeklyTrigger = monthlyTriggerProvider.get();
			weeklyTrigger.setConfig(config);
			return weeklyTrigger;
		}
				

		return null;
	}

	private AbstractTrigger parseWeeklyExpression(StrTokenizer tokenizer) {
		tokenizer = getSubTokenizer(tokenizer, 3);
		
		ExpressionAndSub<Set<Days>> daysExpr = getWeekDays(tokenizer);		
		if(null != daysExpr){
			WeeklyConfig config = new WeeklyConfig();
			config.setWeeklyDays(daysExpr.getExpression());
			
			
			tokenizer = appendTimeOfDay(config, daysExpr.subTokenizer);
			tokenizer = appendStarting(config, tokenizer);
			tokenizer = appendEnd(config, tokenizer);
			
			WeeklyTrigger weeklyTrigger = weeklyTriggerProvider.get();
			weeklyTrigger.setConfig(config);
			return weeklyTrigger;
		}
				

		return null;
	}


	private AbstractTrigger parseTodayAtTimeExpression(StrTokenizer tokenizer) {
		String second = tokenizer.getTokenArray()[1];
		
		StrTokenizer subTokenizer = getSubTokenizer(tokenizer, 2);

		DailyConfig config = new DailyConfig();
		
		Date first = new GregorianCalendar().getTime();
		first = DateUtils.truncate(first, java.util.Calendar.DATE);
		config.setFirstExecution(first);
		
		if("at".equals(second)){
			config.setEndType(EndTypes.COUNT);
			config.setNumberOfExecutions(1);
			
			appendTimeOfDay(config, subTokenizer);
			
			
			DailyNthdayTrigger trigger = dailyNthDayTriggerProvider.get();
			trigger.setConfig(config);
			return trigger;
		} else if("between".equals(second)){
			config.setEndType(EndTypes.DATE);
						
			appendTimeOfDay(config, subTokenizer);
			
			Date last = new GregorianCalendar().getTime();
			last = DateUtils.setHours(last, 23);
			last = DateUtils.setMinutes(last, 59);
			config.setLastExecution(last);
			
			DailyNthdayTrigger trigger = dailyNthDayTriggerProvider.get();
			trigger.setConfig(config);
			return trigger;
		}
		
		return null;
	}
	
	private AbstractTrigger parseDaylyExpression(StrTokenizer tokenizer, DateTrigger trigger) {
		String third = tokenizer.getTokenArray()[2];
		
		StrTokenizer subTokenizer = getSubTokenizer(tokenizer, 4);
		
		if("at".equals(third)){
			ExpressionAndSub<Time> timeExpr = getTimeExpression(subTokenizer);
			if(null != timeExpr){
				DailyConfig config = new DailyConfig();
				config.setAtTime(timeExpr.getExpression());
				
				subTokenizer = appendStarting(config, timeExpr.subTokenizer);
				subTokenizer = appendEnd(config, subTokenizer);
				
				trigger.setConfig(config);
				return trigger;
			}
		}

		return null;
	}

	private AbstractTrigger parseHourlyTrigger(StrTokenizer tokenizer) {
		String third = tokenizer.getTokenArray()[2];
		
		StrTokenizer subTokenizer = getSubTokenizer(tokenizer, 4);
		
		if("at".equals(third)){
			ExpressionAndSub<Integer> timeExpr = getMinuteExpression(subTokenizer);
			if(null != timeExpr){
				DailyConfig config = new DailyConfig();

				config.setDailyRepeatType(DailyRepeatType.BOUNDED_INTERVAL);
				
				config.setTimeRangeInterval(1);
				config.setTimeRangeStart(new Time(0,timeExpr.getExpression()));
				config.setTimeRangeEnd(new Time(23, 59));
				config.setTimeRangeUnit(TimeUnits.HOURS);
				
				subTokenizer = appendStarting(config, timeExpr.subTokenizer);
				subTokenizer = appendEnd(config, subTokenizer);
				
				return new DailyNthdayTrigger(config);
			}
		}
		
		return null;
	}


	private AbstractTrigger parseAtTrigger(StrTokenizer tokenizer) {
		StrTokenizer subTokenizer = getSubTokenizer(tokenizer, 2);
		
		ExpressionAndSub<Date> dateExpr = getDateExpression(subTokenizer);
		if(null != dateExpr){
			DailyConfig config = new DailyConfig();
			config.setAtTime(new Time(dateExpr.getExpression()));
			
			Date first = dateExpr.getExpression();
			first = DateUtils.truncate(first, java.util.Calendar.DATE);
			config.setFirstExecution(first);
			
			config.setEndType(EndTypes.COUNT);
			config.setNumberOfExecutions(1);
			
			DailyNthdayTrigger trigger = dailyNthDayTriggerProvider.get();
			trigger.setConfig(config);
			return trigger;
		}
		
		return null;
	}
	
	private ExpressionAndSub<Set<Days>> getWeekDays(StrTokenizer tokenizer) {
		if(tokenizer.getTokenArray().length < 2)
			throw new IllegalArgumentException("Could not parse days of weeks");
		
		String next = tokenizer.nextToken();
		if(! "on".equals(next))
			throw new IllegalArgumentException("Could not parse days of weeks");

		int cnt = 2;
		Set<Days> list = new HashSet<Days>();
		boolean ok = true;
		while(tokenizer.hasNext() && ok){
			String day = tokenizer.nextToken().replace(",", "");
			if("and".equals(day)){
				cnt++;
				continue;
			} 
			
			ok = false;
			for(Days eDay : Days.values()){
				if(eDay.toString().toLowerCase().equals(day)){
					if(list.contains(eDay))
						throw new IllegalArgumentException("Could not parse days of weeks");
					list.add(eDay);
					cnt++;
					ok = true;
					break;
				}
			}
		}
		
		if(list.isEmpty())
			throw new IllegalArgumentException("Could not parse days of weeks");
		
		return new ExpressionAndSub<Set<Days>>(list, getSubTokenizer(tokenizer, cnt));
	}
	
	
	private ExpressionAndSub<Integer> getMinuteExpression(
			StrTokenizer tokenizer) {
		if(! tokenizer.hasNext())
			return null;
		
		String timeExprStr = tokenizer.nextToken();
		int minutes = (Integer.parseInt(timeExprStr));
		
		return new ExpressionAndSub<Integer>(minutes, getSubTokenizer(tokenizer, 2));
	}

	private ExpressionAndSub<Time> getTimeExpression(StrTokenizer tokenizer) {
		if(! tokenizer.hasNext())
			return null;
		
		String timeExprStr = tokenizer.nextToken();
		String[] fragments = timeExprStr.split(":");
		
		Time time = null;
		
		if(fragments.length == 1)
			time = new Time(Integer.parseInt(fragments[0]), 0);
		else if(fragments.length == 2)
			time = new Time(Integer.parseInt(fragments[0]), Integer.parseInt(fragments[1]));
		else
			return null;
		
		return new ExpressionAndSub<Time>(time, getSubTokenizer(tokenizer, 2));
	}

	private ExpressionAndSub<Date> getDateExpression(StrTokenizer tokenizer) {
		if(tokenizer.getTokenArray().length < 1)
			return null;
		
		String first = tokenizer.getTokenArray()[0];
		
		if(tokenizer.getTokenArray().length > 1){
			String second = tokenizer.getTokenArray()[1];
			
			try{
				if(null != getTimeExpression(getSubTokenizer(tokenizer, 2))){
					String time = first + " " + second;
					try {
						Date date = new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(time);
						
						return new ExpressionAndSub<Date>(date, getSubTokenizer(tokenizer, 3));
					} catch (ParseException e) {
						throw new RuntimeException(e);
					}
				}
			} catch(NumberFormatException e){}
		} 

		String time = first;
		try {
			Date date = new SimpleDateFormat("dd.MM.yyyy").parse(time);
			
			return new ExpressionAndSub<Date>(date, getSubTokenizer(tokenizer, 2));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	

	private ExpressionAndSub<Integer> getDayInMonth(StrTokenizer tokenizer) {
		if(tokenizer.getTokenArray().length < 3)
			throw new IllegalArgumentException("Could not days in month");
		
		String first = tokenizer.getTokenArray()[0];
		String second = tokenizer.getTokenArray()[1];
		String third = tokenizer.getTokenArray()[2];
		
		if(! "on".equals(first))
			throw new IllegalArgumentException("Could not days in month");
		
		if("day".equals(second)){
			int day = Integer.parseInt(third);
			
			return new ExpressionAndSub<Integer>(day, getSubTokenizer(tokenizer, 4));
		}
		
		throw new IllegalArgumentException("Could not days in month");
	}

	
	private StrTokenizer appendTimeOfDay(DateTriggerConfig config,
			StrTokenizer tokenizer) {
		if(tokenizer.getTokenArray().length < 2)
			throw new IllegalArgumentException("Could not parse time of day");
		
		String first = tokenizer.getTokenArray()[0];
		if("at".equals(first)){
			tokenizer = getSubTokenizer(tokenizer, 2);
			
			ExpressionAndSub<Time> timeExpr = getTimeExpression(tokenizer);
			if(null != timeExpr){
				config.setAtTime(timeExpr.getExpression());
				return timeExpr.getSubTokenizer();
			}
		}
		else if("between".equals(first)){
			config.setDailyRepeatType(DailyRepeatType.BOUNDED_INTERVAL);
			
			tokenizer = getSubTokenizer(tokenizer, 2);
			ExpressionAndSub<Time> timeExpr = getTimeExpression(tokenizer);
			if(null == timeExpr)
				throw new IllegalArgumentException("Could not parse time of day");
			config.setTimeRangeStart(timeExpr.getExpression());
			tokenizer = timeExpr.getSubTokenizer();
			
			if(! tokenizer.hasNext() || ! "and".equals(tokenizer.nextToken()))
				throw new IllegalArgumentException("Could not parse time of day");
			
			timeExpr = getTimeExpression(getSubTokenizer(timeExpr.getSubTokenizer(),2));
			if(null == timeExpr)
				throw new IllegalArgumentException("Could not parse time of day");
			config.setTimeRangeEnd(timeExpr.getExpression());
			
			tokenizer = timeExpr.getSubTokenizer();
			if(! tokenizer.hasNext() || ! "every".equals(tokenizer.nextToken()))
				throw new IllegalArgumentException("Could not parse time of day");
			
			if(! tokenizer.hasNext())
				throw new IllegalArgumentException("Could not parse time of day");
			Integer amount = Integer.parseInt(tokenizer.nextToken());
			config.setTimeRangeInterval(amount);
			
			if(! tokenizer.hasNext())
				throw new IllegalArgumentException("Could not parse time of day");
			String unit = tokenizer.nextToken();
			if(! "hours".equals(unit) && ! "minutes".equals(unit))
				throw new IllegalArgumentException("Could not parse time of day");
			
			if( "hours".equals(unit))
				config.setTimeRangeUnit(TimeUnits.HOURS);
			else
				config.setTimeRangeUnit(TimeUnits.MINUTES);
			
			return getSubTokenizer(tokenizer, 4);
		}
			
		
		throw new IllegalArgumentException("Could not parse time of day");
	}


	private StrTokenizer appendStarting(DateTriggerConfig config,
			StrTokenizer tokenizer) {
		if(tokenizer.getTokenArray().length <= 2)
			return tokenizer;
		
		String first = tokenizer.getTokenArray()[0];
		String second = tokenizer.getTokenArray()[1];
		
		if("starting".equals(first) && "on".equals(second)){
			ExpressionAndSub<Date> expr = getDateExpression(getSubTokenizer(tokenizer, 3));
			if(null != expr){
				config.setFirstExecution(expr.getExpression());
				return expr.getSubTokenizer();
			}
		}
		
		return tokenizer;
	}
	

	private StrTokenizer appendEnd(DateTriggerConfig config, StrTokenizer tokenizer) {
		if(tokenizer.getTokenArray().length < 2){
			config.setEndType(EndTypes.INFINITE);
			return tokenizer;
		}
		
		String first = tokenizer.getTokenArray()[0];
		
		if("for".equals(first)){
			if(tokenizer.getTokenArray().length < 3)
				throw new IllegalArgumentException("Could not parse for expression");
		
			String second = tokenizer.getTokenArray()[1];
			String third = tokenizer.getTokenArray()[2];
			
			if(! "times".equals(third))
				throw new IllegalArgumentException("Could not parse for expression");

			config.setEndType(EndTypes.COUNT);
			config.setNumberOfExecutions(Integer.parseInt(second));
			return getSubTokenizer(tokenizer, 4);
		} else if("until".equals(first)){
			ExpressionAndSub<Date> expr = getDateExpression(getSubTokenizer(tokenizer, 2));
			if(null != expr){
				config.setEndType(EndTypes.DATE);
				config.setLastExecution(expr.getExpression());
				return expr.getSubTokenizer();
			} else 
				throw new IllegalArgumentException("Could not parse date expression");
		} else 
			config.setEndType(EndTypes.INFINITE);
		
		return tokenizer;
	}

	
	private StrTokenizer getSubTokenizer(StrTokenizer tokenizer, int i) {
		String[] org = tokenizer.getTokenArray();
		if(org.length <= i-1)
			return new StrTokenizer("");
		String[] newTokens = Arrays.copyOfRange(org, i - 1, org.length);
		
		StrTokenizer newTokenizer = new StrTokenizer(StringUtils.join(newTokens, " "));
		newTokenizer.setDelimiterMatcher(StrMatcher.trimMatcher());
		
		return newTokenizer;
	}
}
