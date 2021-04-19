package net.datenwerke.rs.core.client.reportexporter.dto.decorator;

import net.datenwerke.rs.core.client.reportexporter.dto.RECCsvDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;

/**
 * Dto Decorator for {@link RECCsvDto}
 *
 */
public class RECCsvDtoDec extends RECCsvDto implements ReportExecutionConfigDto {

	private static final long serialVersionUID = 1L;

	public RECCsvDtoDec() {
		super();
		
		setPrintHeader(true);
		setQuote("\"");
		setSeparator(";");
		setLineSeparator("\\r\\n");
		setCharset("UTF-8");
	}

}
