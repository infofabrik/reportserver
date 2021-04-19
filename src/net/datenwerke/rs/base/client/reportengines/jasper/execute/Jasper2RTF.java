package net.datenwerke.rs.base.client.reportengines.jasper.execute;

import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;
import net.datenwerke.rs.core.client.reportexporter.exporter.generic.Export2RTF;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public class Jasper2RTF extends Export2RTF {

	@Override
	public boolean consumes(ReportDto report) {
		return report instanceof JasperReportDto;
	}

}
