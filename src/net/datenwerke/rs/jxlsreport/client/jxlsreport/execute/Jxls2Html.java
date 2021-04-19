package net.datenwerke.rs.jxlsreport.client.jxlsreport.execute;

import net.datenwerke.rs.core.client.reportexporter.exporter.generic.Export2HTML;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportDto;

public class Jxls2Html extends Export2HTML {

	@Override
	public boolean consumes(ReportDto report) {
		return report instanceof JxlsReportDto;
	}

}
