package net.datenwerke.rs.jxlsreport.client.jxlsreport.execute;

import net.datenwerke.rs.core.client.reportexporter.exporter.generic.Export2Excel;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportDto;

public class Jxls2Excel extends Export2Excel {

	@Override
	public boolean consumes(ReportDto report) {
		return report instanceof JxlsReportDto;
	}

}
