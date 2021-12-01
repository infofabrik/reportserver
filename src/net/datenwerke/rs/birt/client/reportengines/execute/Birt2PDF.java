package net.datenwerke.rs.birt.client.reportengines.execute;

import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto;
import net.datenwerke.rs.core.client.reportexporter.exporter.generic.Export2PDF;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public class Birt2PDF extends Export2PDF {

	@Override
	public boolean consumes(ReportDto report) {
		return report instanceof BirtReportDto;
	}

}
