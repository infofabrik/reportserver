package net.datenwerke.rs.core.client.reportexporter.dto.decorator;

import net.datenwerke.rs.core.client.reportexporter.dto.RECJxlsDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;

/**
 * Dto Decorator for {@link RECJxlsDto}
 *
 */
public class RECJxlsDtoDec extends RECJxlsDto implements ReportExecutionConfigDto {


	private static final long serialVersionUID = 1L;

	public RECJxlsDtoDec() {
		super();
		setNumberColumnWidth(8);
		setTextColumnWidth(8);
		setDateColumnWidth(8);
		setCurrencyColumnWidth(8);
		setJxlsReport(false);
		setJxls1(false);
	}

}