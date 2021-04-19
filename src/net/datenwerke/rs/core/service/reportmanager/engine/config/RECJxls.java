package net.datenwerke.rs.core.service.reportmanager.engine.config;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.core.client.reportexporter.dto",
	createDecorator=true
)
public class RECJxls implements ReportExecutionConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = 912269255461484917L;

	@ExposeToClient
	private int numberColumnWidth = 8;
	
	@ExposeToClient
	private int textColumnWidth = 8;

	@ExposeToClient
	private int dateColumnWidth = 8;

	@ExposeToClient
	private int currencyColumnWidth = 8;
	
	@ExposeToClient
	public boolean jxlsReport = false;

	@ExposeToClient
	public boolean jxls1 = true;
	
	public boolean isJxls1() {
		return jxls1;
	}

	public void setJxls1(boolean jxls1) {
		this.jxls1 = jxls1;
	}

	public boolean isJxlsReport() {
		return jxlsReport;
	}

	public void setJxlsReport(boolean jxlsReport) {
		this.jxlsReport = jxlsReport;
	}

	public int getNumberColumnWidth() {
		return numberColumnWidth;
	}

	public void setNumberColumnWidth(int numberColumnWidth) {
		this.numberColumnWidth = numberColumnWidth;
	}
	
	public void setDateColumnWidth(int dateColumnWidth) {
		this.dateColumnWidth = dateColumnWidth;
	}

	public int getTextColumnWidth() {
		return textColumnWidth;
	}

	public int getDateColumnWidth() {
		return dateColumnWidth;
	}

	public void setTextColumnWidth(int textColumnWidth) {
		this.textColumnWidth = textColumnWidth;
	}
	
	public int getCurrencyColumnWidth() {
		return currencyColumnWidth;
	}

	public void setCurrencyColumnWidth(int currencyColumnWidth) {
		this.currencyColumnWidth = currencyColumnWidth;
	}


	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(numberColumnWidth)
			.append(textColumnWidth)
			.append(dateColumnWidth)
			.append(currencyColumnWidth)
			.append(jxlsReport)
			.append(jxls1)
			.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj || ! (obj instanceof RECJxls)) {
			return false;
		}
		RECJxls that = (RECJxls) obj;
		return new EqualsBuilder()
			.append(this.numberColumnWidth, that.numberColumnWidth)
			.append(this.textColumnWidth, that.textColumnWidth)
			.append(this.dateColumnWidth, that.dateColumnWidth)
			.append(this.currencyColumnWidth, that.currencyColumnWidth)
			.append(this.jxlsReport, that.jxlsReport)
			.append(this.jxls1, that.jxls1)
			.isEquals();
	}
	
}