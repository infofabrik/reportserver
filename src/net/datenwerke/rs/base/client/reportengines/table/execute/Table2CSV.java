package net.datenwerke.rs.base.client.reportengines.table.execute;

import com.google.inject.Inject;

import net.datenwerke.gf.client.config.ClientConfigJSONService;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterDao;
import net.datenwerke.rs.core.client.reportexporter.exporter.generic.Export2CSV;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

/**
 * 
 *
 */
public class Table2CSV extends Export2CSV {

	@Inject
	public Table2CSV(ReportExporterDao exporterDao,ClientConfigJSONService jsonService) {
		super(exporterDao, jsonService);
	}

	public boolean consumes(ReportDto report) {
		return report instanceof TableReportDto && !((TableReportDto)report).isCubeFlag();
	}
}
