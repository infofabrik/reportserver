package net.datenwerke.rs.base.client.reportengines.jasper.execute;

import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;
import net.datenwerke.rs.core.client.reportexporter.exporter.generic.Export2Excel;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

/**
 * 
 *
 */
public class Jasper2Excel extends Export2Excel {

	
	public boolean consumes(ReportDto report) {
		return report instanceof JasperReportDto;
	}


}
