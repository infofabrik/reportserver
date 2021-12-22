package net.datenwerke.rs.saiku.client.saiku.reportengines;

import com.google.inject.Inject;

import net.datenwerke.gf.client.config.ClientConfigXmlService;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterDao;
import net.datenwerke.rs.core.client.reportexporter.exporter.generic.Export2CSV;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto;
import net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto;

public class Saiku2CSV extends Export2CSV {

   @Inject
   public Saiku2CSV(ReportExporterDao exporterDao, ClientConfigXmlService jsonService) {
      super(exporterDao, jsonService);
   }

   @Override
   public boolean consumes(ReportDto report) {
      boolean isInstanceOfSaikuReportDto = report instanceof SaikuReportDto;

      if (isInstanceOfSaikuReportDto) {
         DatasourceContainerDto datasourceContainer = report.getDatasourceContainer();
         isInstanceOfSaikuReportDto = !((MondrianDatasourceDto) datasourceContainer.getDatasource()).isMondrian3();

      }

      return isInstanceOfSaikuReportDto || (report instanceof TableReportDto && ((TableReportDto) report).isCubeFlag());
   }

}
