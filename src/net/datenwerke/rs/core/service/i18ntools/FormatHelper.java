package net.datenwerke.rs.core.service.i18ntools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.inject.Inject;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;

public class FormatHelper {

	private String shortDatePattern;
	private String longDatePattern;
	private String shortTimePattern;
	private String longTimePattern;
	private String shortDateTimePattern;
	private String longDateTimePattern;

	private final I18nToolsService i18nToolsService;

	@Inject
	public FormatHelper(I18nToolsService i18nToolsService) {
		this.i18nToolsService = i18nToolsService;
		initPatterns();
	}

	private void initPatterns() {
		FormatPatterns patterns = i18nToolsService.getFormatPatterns();
		shortDatePattern = patterns.getShortDatePattern();
		longDatePattern = patterns.getLongDatePattern();
		shortTimePattern = patterns.getShortTimePattern();
		longTimePattern = patterns.getLongTimePattern();
		shortDateTimePattern = patterns.getShortDateTimePattern();
		longDateTimePattern = patterns.getLongDateTimePattern();
	}

	public String formatShortDate(Date date) {
		SimpleDateFormat format = null;
		if (null != shortDatePattern) {
			format = new SimpleDateFormat(shortDatePattern);
			return format.format(date);
		} else {
			DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT, LocalizationServiceImpl.getLocale());
			return df.format(date);
		}
	}

	public String formatLongDate(Date date) {
		SimpleDateFormat format = null;
		if (null != longDatePattern) {
			format = new SimpleDateFormat(longDatePattern);
			return format.format(date);
		} else {
			DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT, LocalizationServiceImpl.getLocale());
			return df.format(date);
		}
	}

	public String formatShortTime(Date date) {
		SimpleDateFormat format = null;
		if (null != shortTimePattern) {
			format = new SimpleDateFormat(shortTimePattern);
			return format.format(date);
		} else {
			DateFormat df = DateFormat.getTimeInstance(DateFormat.DEFAULT, LocalizationServiceImpl.getLocale());
			return df.format(date);
		}
	}

	public String formatLongTime(Date date) {
		SimpleDateFormat format = null;
		if (null != longTimePattern) {
			format = new SimpleDateFormat(longTimePattern);
			return format.format(date);
		} else {
			DateFormat df = DateFormat.getTimeInstance(DateFormat.DEFAULT, LocalizationServiceImpl.getLocale());
			return df.format(date);
		}
	}

	public String formatShortDateTime(Date date) {
		SimpleDateFormat format = null;
		if (null != shortDateTimePattern) {
			format = new SimpleDateFormat(shortDateTimePattern);
			return format.format(date);
		} else {
			DateFormat df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT,
					LocalizationServiceImpl.getLocale());
			return df.format(date);
		}
	}

	public String formatLongDateTime(Date date) {
		SimpleDateFormat format = null;
		if (null != longDateTimePattern) {
			format = new SimpleDateFormat(longDateTimePattern);
			return format.format(date);
		} else {
			DateFormat df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT,
					LocalizationServiceImpl.getLocale());
			return df.format(date);
		}
	}

}
