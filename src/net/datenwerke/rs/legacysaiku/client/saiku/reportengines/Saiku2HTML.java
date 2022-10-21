package net.datenwerke.rs.legacysaiku.client.saiku.reportengines;

import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.reportexporter.exporter.generic.Export2HTML;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto;
import net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto;

public class Saiku2HTML extends Export2HTML {

   @Override
   public boolean consumes(ReportDto report) {
      if (report instanceof SaikuReportDto) {
         DatasourceContainerDto datasourceContainer = report.getDatasourceContainer();
         return ((MondrianDatasourceDto) datasourceContainer.getDatasource()).isMondrian3();

      }

      return false;
   }

}
