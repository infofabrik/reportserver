package net.datenwerke.rs.core.service.i18ntools;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(dtoPackage="net.datenwerke.rs.core.client.i18tools.dto")
public class FormatPatterns {

	@ExposeToClient private String shortDatePattern;
	@ExposeToClient private String longDatePattern;
	@ExposeToClient private String shortTimePattern;
	@ExposeToClient private String longTimePattern;
	@ExposeToClient private String shortDateTimePattern;
	@ExposeToClient private String longDateTimePattern;

	@ExposeToClient private String numberPattern;
	@ExposeToClient private String currencyPattern;
	@ExposeToClient private String integerPattern;
	@ExposeToClient private String percentPattern;

	
	public FormatPatterns() {
		// TODO Auto-generated constructor stub
	}
	
	public String getShortDatePattern() {
		return shortDatePattern;
	}
	public void setShortDatePattern(String shortDatePattern) {
		this.shortDatePattern = shortDatePattern;
	}
	public String getLongDatePattern() {
		return longDatePattern;
	}
	public void setLongDatePattern(String longDatePattern) {
		this.longDatePattern = longDatePattern;
	}
	public String getShortTimePattern() {
		return shortTimePattern;
	}
	public void setShortTimePattern(String shortTimePattern) {
		this.shortTimePattern = shortTimePattern;
	}
	public String getLongTimePattern() {
		return longTimePattern;
	}
	public void setLongTimePattern(String longTimePattern) {
		this.longTimePattern = longTimePattern;
	}
	public String getShortDateTimePattern() {
		return shortDateTimePattern;
	}
	public void setShortDateTimePattern(String shortDateTimePattern) {
		this.shortDateTimePattern = shortDateTimePattern;
	}
	public String getLongDateTimePattern() {
		return longDateTimePattern;
	}
	public void setLongDateTimePattern(String longDateTimePattern) {
		this.longDateTimePattern = longDateTimePattern;
	}
	public String getNumberPattern() {
		return numberPattern;
	}
	public void setNumberPattern(String numberPattern) {
		this.numberPattern = numberPattern;
	}
	public String getCurrencyPattern() {
		return currencyPattern;
	}
	public void setCurrencyPattern(String currencyPattern) {
		this.currencyPattern = currencyPattern;
	}
	public String getIntegerPattern() {
		return integerPattern;
	}
	public void setIntegerPattern(String integerPattern) {
		this.integerPattern = integerPattern;
	}
	public String getPercentPattern() {
		return percentPattern;
	}
	public void setPercentPattern(String percentPattern) {
		this.percentPattern = percentPattern;
	}
	
	
}
