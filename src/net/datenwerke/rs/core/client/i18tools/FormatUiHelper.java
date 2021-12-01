package net.datenwerke.rs.core.client.i18tools;


import javax.inject.Singleton;

import net.datenwerke.rs.core.client.i18tools.dto.FormatPatternsDto;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;

@Singleton
public class FormatUiHelper {

	private String shortDatePattern;
	private String longDatePattern;
	private String shortTimePattern;
	private String longTimePattern;
	private String shortDateTimePattern;
	private String longDateTimePattern;

	private String numberPattern;
	private String currencyPattern;
	private String integerPattern;
	private String percentPattern;
	
	
	public FormatUiHelper(String shortDatePattern, String longDatePattern,
			String shortTimePattern, String longTimePattern,
			String shortDateTimePattern, String longDateTimePattern,
			String numberPattern, String currencyPattern,
			String integerPattern, String percentPattern) {
		this.shortDatePattern = shortDatePattern;
		this.longDatePattern = longDatePattern;
		this.shortTimePattern = shortTimePattern;
		this.longTimePattern = longTimePattern;
		this.shortDateTimePattern = shortDateTimePattern;
		this.longDateTimePattern = longDateTimePattern;
		this.numberPattern = numberPattern;
		this.currencyPattern = currencyPattern;
		this.integerPattern = integerPattern;
		this.percentPattern = percentPattern;
	}

	public FormatUiHelper() {
		// TODO Auto-generated constructor stub
	}
	
	public void setFormatPatterns(FormatPatternsDto patterns){
		shortDatePattern = patterns.getShortDatePattern();
		longDatePattern = patterns.getLongDatePattern();
		shortTimePattern = patterns.getShortTimePattern();
		longTimePattern = patterns.getLongTimePattern();
		shortDateTimePattern = patterns.getShortDateTimePattern();
		longDateTimePattern = patterns.getLongDateTimePattern();
		
		numberPattern = patterns.getNumberPattern();
		currencyPattern = patterns.getCurrencyPattern();
		integerPattern = patterns.getIntegerPattern();
		percentPattern = patterns.getPercentPattern();
	}

	public DateTimeFormat getShortDateFormat() {
		DateTimeFormat format;
		
		if(null != shortDatePattern)
			format = DateTimeFormat.getFormat(shortDatePattern);
		else
			 format = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT);
			
		return format;
	}

	public DateTimeFormat getLongDateFormat() {
		DateTimeFormat format;
		
		if(null != longDatePattern)
			format = DateTimeFormat.getFormat(longDatePattern);
		else
			 format = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_LONG);
			
		return format;
	}

	public DateTimeFormat getShortTimeFormat() {
		DateTimeFormat format;
		
		if(null != shortTimePattern)
			format = DateTimeFormat.getFormat(shortTimePattern);
		else
			 format = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.TIME_SHORT);
			
		return format;
	}

	public DateTimeFormat getLongTimeFormat() {
		DateTimeFormat format;
		
		if(null != longTimePattern)
			format = DateTimeFormat.getFormat(longTimePattern);
		else
			 format = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.TIME_LONG);
			
		return format;
	}

	public DateTimeFormat getShortDateTimeFormat() {
		DateTimeFormat format;
		
		if(null != shortDateTimePattern)
			format = DateTimeFormat.getFormat(shortDateTimePattern);
		else
			 format = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_SHORT);
			
		return format;
	}

	public DateTimeFormat getLongDateTimeFormat() {
		DateTimeFormat format;
		
		if(null != longDateTimePattern)
			format = DateTimeFormat.getFormat(longDateTimePattern);
		else
			 format = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_TIME_LONG);
			
		return format;
	}

	public NumberFormat getNumberFormat() {
		NumberFormat format;
		
		if(null != numberPattern)
			format = NumberFormat.getFormat(numberPattern);
		else
			 format = NumberFormat.getDecimalFormat();
			
		return format;
	}

	public NumberFormat getCurrencyFormat(String currency) {
		NumberFormat format;
		
		if(null != currencyPattern)
			format = NumberFormat.getFormat(currencyPattern, currency);
		else
			 format = NumberFormat.getCurrencyFormat(currency);
			
		return format;
	}

	public NumberFormat getIntegerFormat() {
		NumberFormat format;
		
		if(null != integerPattern)
			format = NumberFormat.getFormat(integerPattern);
		else
			 format = NumberFormat.getDecimalFormat().overrideFractionDigits(0);
			
		return format;
	}
	
	public NumberFormat getPercentInstance(){
		NumberFormat format;
		
		if(null != percentPattern)
			format = NumberFormat.getFormat(percentPattern);
		else
			 format = NumberFormat.getPercentFormat();
			
		return format;
	}

}
