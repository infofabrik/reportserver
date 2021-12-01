package net.datenwerke.rs.base.client.reportengines.jasper.execute;

import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;
import net.datenwerke.rs.core.client.reportexporter.exporter.generic.Export2PDF;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

/**
 * 
 *
 */
public class Jasper2PDF extends Export2PDF {

	
	public boolean consumes(ReportDto report) {
		return report instanceof JasperReportDto;
	}
	
}
