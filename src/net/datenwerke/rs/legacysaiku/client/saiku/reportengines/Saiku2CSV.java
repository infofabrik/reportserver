package net.datenwerke.rs.legacysaiku.client.saiku.reportengines;

import net.datenwerke.gf.client.config.ClientConfigJSONService;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterDao;
import net.datenwerke.rs.core.client.reportexporter.exporter.generic.Export2CSV;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto;
import net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto;

import com.google.inject.Inject;

public class Saiku2CSV extends Export2CSV {

	@Inject
	public Saiku2CSV(ReportExporterDao exporterDao,ClientConfigJSONService jsonService) {
		super(exporterDao, jsonService);
	}

	@Override
	public boolean consumes(ReportDto report) {
		if (report instanceof SaikuReportDto) {
		    DatasourceContainerDto datasourceContainer = report.getDatasourceContainer();
		    return ((MondrianDatasourceDto)datasourceContainer.getDatasource()).isMondrian3();
			
		}
		
		return false;
	}

}
