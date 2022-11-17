package net.datenwerke.rs.saiku.client.saiku.reportengines;

import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.core.client.reportexporter.exporter.generic.Export2Excel;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto;

public class Saiku2Excel extends Export2Excel {

   @Override
   public boolean consumes(ReportDto report) {
      if (report instanceof SaikuReportDto) {
         return true;
      }

      return report instanceof TableReportDto && ((TableReportDto) report).isCubeFlag();
   }

}
