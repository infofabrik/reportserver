package net.datenwerke.rs.grideditor.client.grideditor.execute;

import net.datenwerke.rs.core.client.reportexporter.exporter.generic.Export2Excel;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorReportDto;

public class GridEditor2Excel extends Export2Excel {

	@Override
	public boolean consumes(ReportDto report) {
		return report instanceof GridEditorReportDto;
	}

}
