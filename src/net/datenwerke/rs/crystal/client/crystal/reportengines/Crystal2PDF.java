package net.datenwerke.rs.crystal.client.crystal.reportengines;

import net.datenwerke.rs.core.client.reportexporter.exporter.generic.Export2PDF;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportDto;

public class Crystal2PDF extends Export2PDF {

   @Override
   public boolean consumes(ReportDto report) {
      return report instanceof CrystalReportDto;
   }

}
