package net.datenwerke.rs.base.client.reportengines.table.execute;

import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.core.client.reportexporter.exporter.generic.Export2HTML;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

/**
 * 
 *
 */
public class Table2HTML extends Export2HTML {

	
	public boolean consumes(ReportDto report) {
		return report instanceof TableReportDto && !((TableReportDto)report).isCubeFlag();
	}
}
